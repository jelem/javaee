package com.tasks.determiners;

import com.tasks.MoviePriceCode;
import com.tasks.Rental;

public class ChildrensAmountDeterminer implements AmountDeterminer {

  @Override
  public boolean correctOption(MoviePriceCode priceCode) {
    return priceCode == MoviePriceCode.CHILDRENS;
  }

  @Override
  public double calculateAmount(Rental rental) {
    return 1.5 + ((rental.getDaysRented() > 3)
        ? (rental.getDaysRented() - 3) * 1.5
        : 0);
  }
}
