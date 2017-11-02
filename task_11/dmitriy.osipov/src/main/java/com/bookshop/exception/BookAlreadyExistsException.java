package com.bookshop.exception;

public class BookAlreadyExistsException extends BookException {

  public BookAlreadyExistsException() {
    this("The book with the same ID already exists in the DB");
  }

  public BookAlreadyExistsException(String message) {
    super(message);
  }
}
