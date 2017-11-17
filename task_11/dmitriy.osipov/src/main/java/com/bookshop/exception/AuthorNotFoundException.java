package com.bookshop.exception;

public class AuthorNotFoundException extends AuthorException {

  public AuthorNotFoundException() {
    this("Author with the same ID not found in the DB");
  }

  public AuthorNotFoundException(String message) {
    super(message);
  }
}
