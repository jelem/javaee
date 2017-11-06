package com.tasks;

import java.util.List;

public class AmountService {

  private int frequentRenterPoints;
  private double totalAmount;
  private Logger logger;

  public AmountService() {
    this(new Logger());
  }

  public AmountService(Logger logger) {
    this.logger = logger;
  }

  public String getStatement(String name, List<Rental> rentals) {
    logger.logName(name);

    calculateTotalAmount(rentals);

    logger.logOwed(totalAmount);
    logger.logTotalRenterPoints(frequentRenterPoints);

    return logger.getResult();
  }

  private void calculateTotalAmount(List<Rental> rentals) {
    totalAmount = 0;
    frequentRenterPoints = 0;
    AmountCalculator calculator = new AmountCalculator();

    for (Rental rental : rentals) {
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
