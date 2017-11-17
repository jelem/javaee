package com.bookshop.exception;

public class BookInvalidException extends BookException {

  public BookInvalidException() {
    this("Some parameters have incorrect values");
  }

  public BookInvalidException(String message) {
    super(message);
  }
}
