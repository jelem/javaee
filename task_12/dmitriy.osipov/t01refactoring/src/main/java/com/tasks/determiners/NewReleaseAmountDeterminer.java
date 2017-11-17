package com.tasks.determiners;

import com.tasks.MoviePriceCode;
import com.tasks.Rental;

public class NewReleaseAmountDeterminer implements AmountDeterminer {

  @Override
  public boolean correctOption(MoviePriceCode priceCode) {
    return priceCode == MoviePriceCode.NEW_RELEASE;
  }

  @Override
  public double calculateAmount(Rental rental) {
    return rental.getDaysRented() * 3;
  }
}
