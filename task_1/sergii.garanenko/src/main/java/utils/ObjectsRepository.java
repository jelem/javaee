package utils;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardOpenOption.APPEND;

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
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.Commit;
import model.FileData;

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
    Set<FileData> changes = fileDataSet
        .stream()
        .filter(fileData -> {
          String commitSHA1 = fileData.getHash();
          return Files.exists(objectsFolderPath.resolve(commitSHA1))
              ? false
              : true;
        }).collect(Collectors.toSet());
    saveSnapshotObjects(changes);
    saveFileDataSet(changes);
    saveCommit(commitName, getObjectSHA1(changes));
  }

  public void getLog() {

  }

  /*
   * method obtain list of full commit data
   * @param commit main commit data
   * @return full commit data list
   * */
  private Set<FileData> getDataSet(Commit commit) throws IOException {
    return readFileDataSet(commit.getCommitSHA1());
  }

  /*
   * method obtain commit data by commit name
   * @return data of commit if it is exist
   * */
  private Optional<Commit> getCommit(final String commitName) throws IOException {
    List<String> commitList = getCommitList();
    return commitList
        .stream()
        .filter(commitString -> {
          String[] split = commitString.split("=");
          return split[1].equals(commitName) ? true : false;
        })
        .map(commitString -> {
          String[] split = commitString.split("=");
          return new Commit(split[0], split[1]);
        })
        .findFirst();
  }

  private List<String> getCommitList() throws IOException {
    return Files.readAllLines(commitListFilePath);
  }

  private void saveSnapshotObjects(Set<FileData> fileDataSet) throws IOException {
    for (FileData fileData : fileDataSet) {
      Path soursePath = Paths.get(
          watchedFolder.getPath(),
          fileData.getParent(),
          fileData.getName()
      );
      try {
        Files.copy(soursePath, objectsFolderPath.resolve(fileData.getHash()), COPY_ATTRIBUTES);
      } catch (FileAlreadyExistsException e) {
        System.out.println("File aleardy exist:" + fileData.getName());
      }
    }
  }

  private void saveCommit(String commitName, String commitSHA1) throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(commitListFilePath, APPEND)) {
      writer.write(commitSHA1 + "=" + commitName);
    }
  }

  /*
  * method saves list of changed files data after last commit saved
  * @param fileDataSet data of changed files
  * */
  private void saveFileDataSet(Set<FileData> fileDataSet) throws IOException {
    String commitSHA1 = getObjectSHA1(fileDataSet);
    try (ObjectOutputStream outputStream = new ObjectOutputStream(
        new FileOutputStream(logsFolderPath.resolve(commitSHA1).toFile()))) {
      outputStream.writeObject(fileDataSet);
    }
  }

  /*
  * method reads list of changed files data by file name
  * @param commitSHA1 file name of some changed files data list
  * @return changed files data list or empty collection
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
    * method creates SHA1-string from any object
    * @param object any object
    * @return SHA1-string of object
    * */
  private String getObjectSHA1(Object object) throws IOException {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
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
