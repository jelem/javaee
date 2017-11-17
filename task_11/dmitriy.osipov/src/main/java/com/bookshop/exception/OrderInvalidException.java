package com.bookshop.exception;

public class OrderInvalidException extends OrderException {

  public OrderInvalidException() {
    this("Some parameters have incorrect values");
  }

  public OrderInvalidException(String message) {
    super(message);
  }
}
