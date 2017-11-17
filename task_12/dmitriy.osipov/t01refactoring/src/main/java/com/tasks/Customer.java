package com.tasks;

import java.util.Vector;

public class Customer {

  private String name;
  private Vector<Rental> rentals;
  private AmountService amountService;

  public Customer(String name) {
    this(name, new AmountService());
  }

  public Customer(String name, AmountService amountService) {
    this.name = name;
    this.amountService = amountService;
    this.rentals = new Vector<>();
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return amountService.getStatement(name, rentals);
  }
}
