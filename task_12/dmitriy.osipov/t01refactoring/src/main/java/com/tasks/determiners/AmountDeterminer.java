package com.tasks.determiners;

import com.tasks.MoviePriceCode;
import com.tasks.Rental;

public interface AmountDeterminer {

  boolean correctOption(MoviePriceCode priceCode);

  double calculateAmount(Rental rental);
}
