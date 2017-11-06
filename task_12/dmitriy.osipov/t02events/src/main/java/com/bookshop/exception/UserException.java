package com.bookshop.exception;

public class UserException extends Exception {

  public UserException() {
    this("An User exception occurred");
  }

  public UserException(String message) {
    super(message);
  }
}
