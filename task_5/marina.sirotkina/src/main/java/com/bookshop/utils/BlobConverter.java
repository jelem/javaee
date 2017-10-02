package com.bookshop.utils;

import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;

public class BlobConverter implements Serializable {

  public Blob readImage(String path) {

    Blob blob = null;
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(path)) {
      byte[] bytes = ByteStreams.toByteArray(is);
      blob = new SerialBlob(bytes);
    } catch (SQLException | IOException ex) {
      ex.printStackTrace();
    }
    return blob;
  }

  public Blob readImage(InputStream stream) {
    Blob blob = null;
    try {
      byte[] bytes = ByteStreams.toByteArray(stream);
      blob = new SerialBlob(bytes);
    } catch (SQLException | IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        stream.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return blob;
  }

  public String readImageFromDb(Blob img) {
    String encodeBytes = "";
    try {
      byte[] bytes = Base64.getEncoder().encode(img.getBytes(1, (int) img.length()));
      encodeBytes = new String(bytes, Charset.forName("Utf-8"));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return encodeBytes;
  }

}
