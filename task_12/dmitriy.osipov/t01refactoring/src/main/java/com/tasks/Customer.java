package com.tasks;

import java.util.Vector;

public class Customer {

  private String name;
  private Vector<Rental> rentals;

  private int frequentRenterPoints;
  private double totalAmount;

  private Logger logger;

  public Customer(String name) {
    this.name = name;
    this.rentals = new Vector<>();
    this.logger = new Logger();
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    logger.logName(name);

    calculateTotalAmount();

    logger.logOwed(totalAmount);
    logger.logTotalRenterPoints(frequentRenterPoints);

    return logger.getResult();
  }

  private void calculateTotalAmount() {
    totalAmount = 0;
    frequentRenterPoints = 0;
    AmountCalculator calculator = new AmountCalculator();

    for (Rental rental : this.rentals) {
      double thisAmount = calculator.calculate(rental);
      logger.logAmount(rental.getMovie().getTitle(), thisAmount);

      growRenterPointsForNewRelease(rental);

      totalAmount += thisAmount;
    }
  }

  private void growRenterPointsForNewRelease(Rental rental) {
    frequentRenterPoints += (rental.getMovie().getPriceCode() == MoviePriceCode.NEW_RELEASE
        && rental.getDaysRented() > 1) ? 2 : 1;
  }
}
