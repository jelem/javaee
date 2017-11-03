package com.bookshop.exception;

public class UserInvalidException extends UserException {

  public UserInvalidException() {
    this("Some fields have incorrect values");
  }

  public UserInvalidException(String message) {
    super(message);
  }
}
