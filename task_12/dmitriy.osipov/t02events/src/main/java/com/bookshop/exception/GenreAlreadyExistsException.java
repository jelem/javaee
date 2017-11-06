package com.bookshop.exception;

public class GenreAlreadyExistsException extends GenreException {

  public GenreAlreadyExistsException() {
   this("Genre with the same ID already exists in the DB");
  }

  public GenreAlreadyExistsException(String message) {
    super(message);
  }
}
