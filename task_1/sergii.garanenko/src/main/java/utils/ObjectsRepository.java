package utils;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardOpenOption.APPEND;

import model.Commit;
import model.FileData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsRepository {

  private File watchedFolder;

  @Value("${rootFolderName}")
  private String rootFolderName;
  private Path rootFolderPath;

  @Value("${objectsFolderName}")
  private String objectsFolderName;
  private Path objectsFolderPath;

  @Value("${logsFolderName}")
  private String logsFolderName;
  private Path logsFolderPath;

  @Value("${configFolderName}")
  private String configFolderName;
  private Path configFolderPath;

  @Value("${commitListFile}")
  private String commitListFileName;
  private Path commitListFilePath;


  public void createStorage(File watchedFolder) throws IOException {
    this.watchedFolder = watchedFolder;

    rootFolderPath = Paths.get(watchedFolder.getPath(), rootFolderName);
    objectsFolderPath = rootFolderPath.resolve(objectsFolderName);
    logsFolderPath = rootFolderPath.resolve(logsFolderName);
    configFolderPath = rootFolderPath.resolve(configFolderName);
    commitListFilePath = logsFolderPath.resolve(commitListFileName);
    if (!new File(watchedFolder, rootFolderName).exists()) {
      Files.createDirectory(rootFolderPath);
      Files.createDirectory(objectsFolderPath);
      Files.createDirectory(logsFolderPath);
      Files.createDirectory(configFolderPath);
      Files.createFile(commitListFilePath);
    }
  }

  public void saveSnapshot(Set<FileData> fileDataSet, String commitName) throws IOException {
    saveSnapshotObjects(fileDataSet);
    saveFileDataSet(fileDataSet);
    saveCommit(new Commit(getObjectSHA1(fileDataSet), commitName));
  }

  public String getLog() {
    try {
      List<Commit> commitList = getCommitList();
      return commitList
          .stream()
          .map(commit -> MessageFormat.format(
              "Commit name:{0}{1}{2}",
              commit.getCommitName(),
              "\n",
              commit.getCommitSHA1())
          )
          .collect(Collectors.joining("\n"));
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  /*
   * method revert project by commit
   * @param commitName name of commit
   * */
  public void revert(String commitName) throws IOException {
    Optional<Commit> commit = getCommit(commitName);
    Set<FileData> fileDataSet = commit.isPresent()
        ? readFileDataSet(commit.get().getCommitSHA1())
        : Collections.emptySet();
    if (!fileDataSet.isEmpty()) {
      cleanWatchedProject();
    }
    recovery(fileDataSet);
  }

  private void recovery(Set<FileData> fileDataSet) throws IOException {
    for (FileData fileData : fileDataSet) {
      Path soursePath = Paths.get(objectsFolderPath.toString(), fileData.getHash());
      Path destinationPath = Paths
          .get(watchedFolder.toPath().toString(), fileData.getParent(), fileData.getName());
      Path parent = destinationPath.getParent();
      if (parent == null) {
        return;
      }
      createPath(parent);
      Files.copy(soursePath, destinationPath, COPY_ATTRIBUTES);
    }
  }

  private void createPath(Path path) throws IOException {
    int nameCount = path.getNameCount();
    int namePointer = nameCount;
    Path startPath = path;
    while (!startPath.toFile().exists()) {
      namePointer--;
      startPath = startPath.getParent();
      if (startPath == null) {
        break;
      }
    }
    while (nameCount != namePointer) {
      startPath = Files
          .createDirectory(Paths.get(startPath.toString(), path.getName(namePointer).toString()));
      namePointer++;
    }
  }

  private void cleanWatchedProject() {
    File[] files = watchedFolder
        .listFiles(pathname -> !pathname.getPath().equals(rootFolderPath.toString()));
    if (files != null) {
      for (File file : files) {
        delete(file);
      }
    }
  }

  private void delete(File file) {
    if (!file.exists()) {
      return;
    }
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (files != null) {
        for (File childFile : files) {
          delete(childFile);
        }
        if (!file.delete()) {
          System.out.println(String.format("Directory %s wasn't deleted", file.toString()));
        }
      }
    } else {
      if (!file.delete()) {
        System.out.println(String.format("File %s wasn't deleted", file.toString()));
      }
    }
  }

  /*
  * method reads list of changed files data by file name
  * @param commitSHA1 file name of some changed files data list
  * @return data of all watched files at the time of specified commit or empty collection
  * */
  private Set<FileData> readFileDataSet(String commitSHA1) throws IOException {
    try {
      try (ObjectInputStream inputStream = new ObjectInputStream(
          new FileInputStream(logsFolderPath.resolve(commitSHA1).toFile()))) {
        return (Set<FileData>) inputStream.readObject();
      }
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  /*
   * method obtains commit data by commit name
   * @return data of commit if it is exist
   * */
  private Optional<Commit> getCommit(final String commitName) throws IOException {
    List<Commit> commitList = getCommitList();
    return commitList
        .stream()
        .filter(commit -> commit.getCommitName().equals(commitName) ? true : false)
        .findFirst();
  }

  /*
    * method obtains all commit list
    * @return all commit list if it is exist
    * */
  private List<Commit> getCommitList() throws IOException {
    List<String> lines = Files.readAllLines(commitListFilePath);
    return lines
        .stream()
        .map(line -> {
          String[] split = line.split("=");
          return new Commit(split[0], split[1]);
        })
        .collect(Collectors.toList());
  }

  /*
    * method saves files from watched directories
    * @param fileDataSet data of all watched files
    * */
  private void saveSnapshotObjects(Set<FileData> fileDataSet) throws IOException {
    for (FileData fileData : fileDataSet) {
      Path soursePath = Paths.get(
          watchedFolder.getPath(),
          fileData.getParent(),
          fileData.getName()
      );
      try {
        Files.copy(soursePath, objectsFolderPath.resolve(fileData.getHash()), COPY_ATTRIBUTES);
      } catch (FileAlreadyExistsException ex) {
        System.out.println("File aleardy exist:" + fileData.getName());
      }
    }
  }

  /*
  * method saves commit record if it is not exist
  * @param commit
  * */
  private void saveCommit(Commit commit) throws IOException {
    if (!getCommitList().contains(commit)) {
      try (BufferedWriter writer = Files.newBufferedWriter(commitListFilePath, APPEND)) {
        writer.write(commit.getCommitSHA1() + "=" + commit.getCommitName() + "\n");
      }
    }
  }

  /*
  * method saves list of changed files data after last commit saved
  * @param fileDataSet data of all watched files
  * */
  private void saveFileDataSet(Set<FileData> fileDataSet) throws IOException {
    String commitSHA1 = getObjectSHA1(fileDataSet);
    try (ObjectOutputStream outputStream = new ObjectOutputStream(
        new FileOutputStream(logsFolderPath.resolve(commitSHA1).toFile()))) {
      outputStream.writeObject(fileDataSet);
    }
  }

  /*
    * method creates SHA1-string from any object
    * @param object any object
    * @return SHA1-string of object
    * */
  private String getObjectSHA1(Object object) throws IOException {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException("NoSuchAlgorithmException", ex);
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
      out.writeObject(object);
    }
    digest.update(baos.toByteArray());
    try (Formatter formatter = new Formatter()) {
      for (final byte b : digest.digest()) {
        formatter.format("%02x", b);
      }
      return formatter.toString();
    }
  }
}
