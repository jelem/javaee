package com.task.bookshop.exceptions;

public class BookAlreadyExistsException extends RuntimeException {

  public BookAlreadyExistsException(String message) {
    super(message);
  }
}
