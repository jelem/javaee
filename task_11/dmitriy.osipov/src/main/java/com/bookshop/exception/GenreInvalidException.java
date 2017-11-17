package com.bookshop.exception;

public class GenreInvalidException extends GenreException {

  public GenreInvalidException() {
    this("Some parameters have incorrect values");
  }

  public GenreInvalidException(String message) {
    super(message);
  }
}
