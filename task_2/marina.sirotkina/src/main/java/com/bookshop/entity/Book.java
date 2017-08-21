package com.bookshop.entity;

import java.io.Serializable;

public class Book implements Serializable {

  private String title;
  private String author;
  private String year;

  public Book(String title, String author, String year) {
    this.title = title;
    this.author = author;
    this.year = year;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Book: ");
    sb.append("title= ").append(title).append('\'');
    sb.append(", author= ").append(author).append('\'');
    sb.append(", year= ").append(year).append('\'');
    return sb.toString();
  }
}
