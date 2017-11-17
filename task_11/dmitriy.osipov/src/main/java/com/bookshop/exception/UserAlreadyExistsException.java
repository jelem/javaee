package com.bookshop.exception;

public class UserAlreadyExistsException extends UserException {

  public UserAlreadyExistsException() {
    this("Such user already exists");
  }

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
