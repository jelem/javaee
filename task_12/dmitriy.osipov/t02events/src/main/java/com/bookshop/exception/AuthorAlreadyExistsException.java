package com.bookshop.exception;

public class AuthorAlreadyExistsException extends AuthorException {

  public AuthorAlreadyExistsException() {
    this("Author with the same ID already exists in the DB");
  }

  public AuthorAlreadyExistsException(String message) {
    super(message);
  }
}
