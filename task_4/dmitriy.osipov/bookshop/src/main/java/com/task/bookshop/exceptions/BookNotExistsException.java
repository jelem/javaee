package com.task.bookshop.exceptions;

public class BookNotExistsException extends RuntimeException {

  public BookNotExistsException(String message) {
    super(message);
  }
}
