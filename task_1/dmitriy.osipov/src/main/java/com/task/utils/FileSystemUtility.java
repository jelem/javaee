package com.task.utils;

import com.task.model.Element;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemUtility {

  private String rootFolder;

  public FileSystemUtility(String rootFolder) {
    this.rootFolder = rootFolder;
    this.checkFolder("");
  }

  public FileSystemUtility() {
    this("results");
  }

  public void save(Element element) {
    if (element == null) {
      return;
    }
    String hash = element.getHash();
    File folder = this.checkFolder(hash);
    this.saveObject(element, getFileName(folder, hash));
  }

  public void saveEncrypted(Element element) {
    byte[] asBytes = DataUtility.getObjectAsByteArray(element);
    String hash = element.getHash();
    File folder = this.checkFolder(hash);
    byte[] asBytesEncrypted = DigestUtils.sha1(asBytes);
    try (FileOutputStream outputStream = new FileOutputStream(getFileName(folder, hash))) {
      outputStream.write(asBytesEncrypted);
    } catch (IOException ioExc) {
      ioExc.printStackTrace();
    }
  }

  private String getFileName(File folder, String hash) {
    return folder.getAbsolutePath().concat("\\").concat(hash);
  }

  private void saveObject(Element element, String filename) {
    try (FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(element);
      oos.flush();
    } catch (IOException ioExc) {
      ioExc.printStackTrace();
    }
  }

  private File checkFolder(String elementHash) {
    String path = rootFolder;
    if (elementHash.length() > 2) {
      path = path.concat("\\").concat(elementHash.substring(0, 2));
    }
    File folder = new File(path);
    boolean mkdir;
    if (!Files.exists(Paths.get(path))) {
      mkdir = folder.mkdir();
    }
    return folder;
  }
}
