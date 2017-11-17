package com.tasks;

import com.tasks.determiners.AmountDeterminer;
import com.tasks.determiners.ChildrensAmountDeterminer;
import com.tasks.determiners.NewReleaseAmountDeterminer;
import com.tasks.determiners.RegularAmountDeterminer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmountCalculator {

  private List<AmountDeterminer> determiners;

  public AmountCalculator() {
    determiners = new ArrayList<>(
        Arrays.asList(
            new ChildrensAmountDeterminer(),
            new NewReleaseAmountDeterminer(),
            new RegularAmountDeterminer()
        )
    );
  }

  public double calculate(Rental rental) {
    for (AmountDeterminer determiner : determiners) {
      if (determiner.correctOption(rental.getMovie().getPriceCode())) {
        return determiner.calculateAmount(rental);
      }
    }
    throw new IllegalArgumentException();
  }
}
