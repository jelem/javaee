package com.bookshop.exception;

public class BookException extends Exception {

  public BookException() {
    this("An Book exception occurred");
  }

  public BookException(String message) {
    super(message);
  }
}
