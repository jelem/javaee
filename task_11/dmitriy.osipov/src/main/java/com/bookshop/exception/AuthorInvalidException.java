package com.bookshop.exception;

public class AuthorInvalidException extends AuthorException {

  public AuthorInvalidException() {
    this("Some parameters have incorrect values");
  }

  public AuthorInvalidException(String message) {
    super(message);
  }
}
