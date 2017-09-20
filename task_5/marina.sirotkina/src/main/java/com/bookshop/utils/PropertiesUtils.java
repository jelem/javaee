package com.bookshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

  public static Properties readProperties() {
    Properties properties = new Properties();
    try (InputStream is = ConnectionUtil.class.getClassLoader()
        .getResourceAsStream("db.properties")) {
      properties.load(is);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return properties;
  }
}
