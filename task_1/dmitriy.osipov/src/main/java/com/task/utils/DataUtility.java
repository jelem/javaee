package com.task.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataUtility {

  public static String getHash(Object object) {
    String hash = null;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
        out.writeObject(object);
        hash = DigestUtils.sha1Hex(baos.toByteArray());
        out.close();
      } catch (IOException exc) {
        exc.printStackTrace();
      }
    } catch (IOException exc) {
      exc.printStackTrace();
    }
    return hash;
  }

  public static byte[] getObjectAsByteArray(Object object) {
    byte[] result = null;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
        out.writeObject(object);
        result = baos.toByteArray();
        out.close();
      } catch (IOException exc) {
        exc.printStackTrace();
      }
    } catch (IOException exc) {
      exc.printStackTrace();
    }
    return result;
  }

}
