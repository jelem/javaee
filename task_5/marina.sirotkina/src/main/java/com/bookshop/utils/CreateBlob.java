package com.bookshop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class CreateBlob {

  public Blob readImage(String path) {

    Blob blob = null;
    try {
      byte[] bytes = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResources(path).toString()));
      blob = new SerialBlob(bytes);
    } catch (SQLException | IOException ex) {
      ex.printStackTrace();
    }
    return blob;
  }

}
