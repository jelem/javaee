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
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    Book book = (Book) object;

    if (!title.equals(book.title)) {
      return false;
    }
    if (!author.equals(book.author)) {
      return false;
    }
    return year.equals(book.year);
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + author.hashCode();
    result = 31 * result + year.hashCode();
    return result;
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
