package services;

import model.FileData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class StructureService {

  @Value("${rootFolderName}")
  private String rootFolderName;
  private String rootVCSFullName;
  private File watchedFolder;
  private static final int defaultBufferSize = 8192;

  public void setWatchedFolder(File watchedFolder) {
    this.watchedFolder = watchedFolder;
    rootVCSFullName = Paths.get(watchedFolder.toString(), rootFolderName).toString();
  }

  public Set<FileData> getProjectStructure() {
    Set<FileData> fileDataSet = new HashSet<>();
    List<File> files = getAllFiles(watchedFolder);
    for (File file : files) {
      try {
        fileDataSet.add(new FileData(getFileHash(file), file.getName(), getParent(file)));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return fileDataSet;
  }

  private String getFileHash(File file) throws IOException {

    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException("NoSuchAlgorithmException", ex);
    }
    try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file),
        defaultBufferSize)) {
      byte[] bytes = new byte[defaultBufferSize];
      for (int read = 0; (read = inputStream.read(bytes)) != -1; ) {
        digest.update(bytes, 0, read);
      }
    }

    try (Formatter formatter = new Formatter()) {
      for (final byte b : digest.digest()) {
        formatter.format("%02x", b);
      }
      return formatter.toString();
    }
  }

  private String getParent(File file) {
    Path relativize = watchedFolder.toPath().relativize(file.toPath());
    Path parent = relativize.getParent();
    return parent == null
        ? ""
        : parent.toString();
  }

  private List<File> getAllFiles(File root) {
    List<File> fileList = new ArrayList<>();
    File[] files = root.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          fileList.add(file);
        } else {
          if (!file.toString().startsWith(rootVCSFullName)) {
            fileList.addAll(getAllFiles(file));
          }
        }
      }
    }
    return fileList;
  }
}
