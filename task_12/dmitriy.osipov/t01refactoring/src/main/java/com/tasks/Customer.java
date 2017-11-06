package com.tasks;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    AmountCalculator calculator = new AmountCalculator();
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    Enumeration rentals = this.rentals.elements();
    String result = "Rental Record for " + getName() + "\n";

    while (rentals.hasMoreElements()) {
      double thisAmount = 0;
      Rental each = (Rental) rentals.nextElement();

      // determines the amount for each line
      thisAmount += calculator.calculate(each);

      frequentRenterPoints++;

      if (each.getMovie().getPriceCode() == MoviePriceCode.NEW_RELEASE
          && each.getDaysRented() > 1) {
        frequentRenterPoints++;
      }

      result += "\t" + each.getMovie().getTitle() + "\t"
          + String.valueOf(thisAmount) + "\n";
      totalAmount += thisAmount;

    }

    result += "You owed " + String.valueOf(totalAmount) + "\n";
    result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

    return result;
  }


  private String name;
  private Vector rentals = new Vector();
}
