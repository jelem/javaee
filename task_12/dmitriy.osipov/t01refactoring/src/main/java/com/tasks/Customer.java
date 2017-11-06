package com.tasks;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

  private int frequentRenterPoints;

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
    frequentRenterPoints = 0;
    double totalAmount = 0;
    Enumeration rentals = this.rentals.elements();
    StringBuilder result = new StringBuilder(String.format("Rental Record for %s\n", getName()));

    while (rentals.hasMoreElements()) {
      double thisAmount = 0;
      Rental each = (Rental) rentals.nextElement();

      // determines the amount for each line
      thisAmount += calculator.calculate(each);

      frequentRenterPoints++;

      growRenterPointsForNewRelease(each);

      result.append(
          String.format("\t%s\t%s\n", each.getMovie().getTitle(), String.valueOf(thisAmount)));
      totalAmount += thisAmount;

    }

    result.append(String.format("You owed %s\n", String.valueOf(totalAmount)));
    result.append(String
        .format("You earned %s frequent renter points\n", String.valueOf(frequentRenterPoints)));

    return result.toString();
  }

  private void growRenterPointsForNewRelease(Rental each) {
    if (each.getMovie().getPriceCode() == MoviePriceCode.NEW_RELEASE
        && each.getDaysRented() > 1) {
      frequentRenterPoints++;
    }
  }


  private String name;
  private Vector rentals = new Vector();
}
