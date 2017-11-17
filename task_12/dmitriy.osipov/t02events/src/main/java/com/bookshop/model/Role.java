package com.bookshop.model;

public enum Role {
  ADMIN(0),
  CUSTOMER(1);

  private long id;

  Role(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public static Role createRole(long id) {
    switch ((int) id) {
      case 0:
        return ADMIN;
      default:
        return CUSTOMER;
    }
  }

}
