package services;


import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.FileData;


@Service
public class StructureService {

  private File rootFile;
  private final int DEFAULT_BUFFER_SIZE = 8192;

  public void setRootFile(File rootFile) {
    this.rootFile = rootFile;
  }

  public Set<FileData> getProjectStructure() {
    Set<FileData> fileDataSet = new HashSet<>();
    List<File> files = getAllFiles(rootFile);
    for (File file : files) {
      try {
        fileDataSet.add(new FileData(getFileHash(file), file.getName(), getParent(file)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return fileDataSet;
  }

  private String getFileHash(File file) throws IOException {

    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file),
        DEFAULT_BUFFER_SIZE)) {
      byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
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
    Path relativize = rootFile.toPath().relativize(file.toPath());
    Path parent = relativize.getParent();
    return parent == null
        ? ""
        : parent.toString();
  }

  private List<File> getAllFiles(File root) {
    List<File> fileList = new ArrayList<>();
    File[] files = root.listFiles();
    for (File file : files) {
      if (file.isFile()) {
        fileList.add(file);
      } else {
        fileList.addAll(getAllFiles(file));
      }
    }
    return fileList;
  }
//
//  public static void main(String[] args) throws Exception {
//    StructureService structureService = new StructureService();
//    for (FileData fileData : structureService.getProjectStructure()) {
//      System.out.println(fileData);
//    }
//
////    File file = structureService.getAllFiles(rootFile).get(3);
////    System.out.println(structureService.getParent(file));
////    System.out.println(structureService.getFileHash(file));
//  }
}
