package com.bookshop.exception;

public class OrderException extends Exception {

  public OrderException() {
    this("Some error with order occurred");
  }

  public OrderException(String message) {
    super(message);
  }
}
