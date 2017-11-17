package com.tasks;

public class Logger {

  private StringBuilder builder = new StringBuilder();

  private void logLine(String line) {
    builder.append(line).append('\n');
  }

  public void logName(String name) {
    logLine(String.format("Rental Record for %s", name));
  }

  public void logOwed(double totalAmount) {
    logLine(String.format("You owed %s", String.valueOf(totalAmount)));
  }

  public void logTotalRenterPoints(int renterPoints) {
    logLine(String.format("You earned %s frequent renter points", String.valueOf(renterPoints)));
  }

  public void logAmount(String title, double rentalAmount) {
    logLine(String.format("\t%s\t%s", title, String.valueOf(rentalAmount)));
  }

  public String getResult() {
    return builder.toString();
  }

}
