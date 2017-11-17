package com.bookshop.exception;

public class OrderNotFoundException extends OrderException {

  public OrderNotFoundException() {
    this("Such order not found in DB");
  }

  public OrderNotFoundException(String message) {
    super(message);
  }
}
