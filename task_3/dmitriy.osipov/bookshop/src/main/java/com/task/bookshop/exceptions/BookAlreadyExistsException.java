package com.task.bookshop.exceptions;

public class BookAlreadyExistsException extends Exception {

  public BookAlreadyExistsException(String message) {
    super(message);
  }
}
