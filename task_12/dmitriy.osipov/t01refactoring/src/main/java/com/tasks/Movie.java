package com.tasks;

public class Movie {

  private String title;
  private MoviePriceCode priceCode;

  public Movie(String title, MoviePriceCode priceCode) {
    this.title = title;
    this.priceCode = priceCode;
  }

  public MoviePriceCode getPriceCode() {
    return priceCode;
  }

  public void setPriceCode(MoviePriceCode code) {
    priceCode = code;
  }

  public String getTitle() {
    return title;
  }

}
