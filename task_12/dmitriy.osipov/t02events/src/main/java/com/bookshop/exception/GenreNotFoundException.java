package com.bookshop.exception;

public class GenreNotFoundException extends GenreException {

  public GenreNotFoundException() {
    this("The genre with the same ID not found in the DB");
  }

  public GenreNotFoundException(String message) {
    super(message);
  }
}
