package com.tasks.determiners;

import com.tasks.MoviePriceCode;
import com.tasks.Rental;

public class RegularAmountDeterminer implements AmountDeterminer {

  @Override
  public boolean correctOption(MoviePriceCode priceCode) {
    return priceCode == MoviePriceCode.REGULAR;
  }

  @Override
  public double calculateAmount(Rental rental) {
    return 2 + (
        (rental.getDaysRented() > 2)
            ? (rental.getDaysRented() - 2) * 1.5
            : 0);
  }
}
