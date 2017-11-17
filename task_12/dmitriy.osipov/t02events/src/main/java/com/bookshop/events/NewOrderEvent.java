package com.bookshop.events;

import com.bookshop.model.Order;

public class NewOrderEvent {

  private Order order;

  public NewOrderEvent(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
