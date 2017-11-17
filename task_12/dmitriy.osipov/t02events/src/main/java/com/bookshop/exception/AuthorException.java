package com.bookshop.exception;

public class AuthorException extends Exception {

  public AuthorException() {
    this("An Author exception occurred");
  }

  public AuthorException(String message) {
    super(message);
  }
}
