package com.bookshop.exception;

public class GenreException extends Exception {

  public GenreException() {
    this("An genre exception occurred");
  }

  public GenreException(String message) {
    super(message);
  }
}
