package com.bookshop.exception;

public class OrderAlreadyExistsException extends OrderException {

  public OrderAlreadyExistsException() {
    this("Order with same ID already exists in the DB");
  }

  public OrderAlreadyExistsException(String message) {
    super(message);
  }
}
