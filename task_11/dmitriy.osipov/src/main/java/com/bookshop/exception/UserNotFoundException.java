package com.bookshop.exception;

public class UserNotFoundException extends UserException {

  public UserNotFoundException() {
    this("The user with the same ID not found in the DB");
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
