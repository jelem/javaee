package com.task.bookshop.exceptions;

public class DBConnectionException extends RuntimeException {

  public DBConnectionException() {
  }

  public DBConnectionException(String message) {
    super(message);
  }
}
