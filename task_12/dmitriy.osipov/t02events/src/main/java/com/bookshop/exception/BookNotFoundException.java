package com.bookshop.exception;

public class BookNotFoundException extends BookException {

  public BookNotFoundException() {
    this("The book with the same ID not found in the DB");
  }

  public BookNotFoundException(String message) {
    super(message);
  }
}
