package com.bookshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
  OK(0, "Operation success"),
  ADDED(1, "Object added"),
  UPDATED(2, "Object updated"),
  DELETED(3, "Object deleted"),
  ERROR(1000, "An error occurred"),
  AUTHOR_ERROR(1001, "Some problems with books author"),
  AUTHOR_EXISTS(1002, "Author already exists"),
  AUTHOR_NOT_FOUND(1003, "Author not found"),
  AUTHOR_INVALID(1004, "Some fields have incorrect values"),
  BOOK_ERROR(1010, "Some problems with book"),
  BOOK_EXISTS(1011, "Book already exists"),
  BOOK_NOT_FOUND(1012, "Book not found"),
  BOOK_INVALID(1013, "Some fields have incorrect values"),
  GENRE_ERROR(1020, "Some problems with book's genre"),
  GENRE_EXISTS(1021, "Genre already exists"),
  GENRE_NOT_FOUND(1022, "Genre not found"),
  GENRE_INVALID(1023, "Some fields have incorrect values"),
  USER_ERROR(1030, "Some problems with user"),
  USER_EXISTS(1031, "User already exists"),
  USER_NOT_FOUND(1032, "User not found"),
  USER_INVALID(1033, "Some fields have incorrect values"),
  ORDER_ERROR(1040, "Some problems with order"),
  ORDER_EXISTS(1041, "Order already exists"),
  ORDER_NOT_FOUND(1042, "Order not found"),
  ORDER_INVALID(1043, "Some fields have incorrect values");

  Status(int code, String message) {
    this.code = code;
    this.message = message;
  }

  private int code;
  private String message;

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "{"
        + "code=" + code
        + ", message='" + message + '\''
        + '}';
  }
}
