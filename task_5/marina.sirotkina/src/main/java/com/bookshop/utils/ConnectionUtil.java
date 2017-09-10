package com.bookshop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

  private Connection connection;
  private Properties dbProps = PropertiesUtils.readProperties();

  public ConnectionUtil() {
  }


  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        return connection = DriverManager.getConnection(dbProps.getProperty("db.url"),
            dbProps.getProperty("db.user"), dbProps.getProperty("db.password"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return connection;
  }
}
