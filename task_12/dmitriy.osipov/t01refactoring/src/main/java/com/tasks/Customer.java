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
      Rental each = (Rental) rentals.nextElement();
      double thisAmount = calculator.calculate(each);
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

  private void growRenterPointsForNewRelease(Rental rental) {
    frequentRenterPoints += (rental.getMovie().getPriceCode() == MoviePriceCode.NEW_RELEASE
        && rental.getDaysRented() > 1) ? 2 : 1;
  }


  private String name;
  private Vector rentals = new Vector();
}
