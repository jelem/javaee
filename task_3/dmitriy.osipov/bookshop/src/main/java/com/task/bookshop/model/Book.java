package com.task.bookshop.model;

import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {

  private static final long serialVersionUID = 170820000000L;

  private String id;
  private String title;
  private String author;
  private int year;

  public Book(String title, String author, int year) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
    this.author = author;
    this.year = year;
  }

  private Book() {
  }

  private void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getYear() {
    return year;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Book book = (Book) obj;

    if (year != book.year) {
      return false;
    }
    if (title != null ? !title.equals(book.title) : book.title != null) {
      return false;
    }
    return author != null ? author.equals(book.author) : book.author == null;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + year;
    return result;
  }

  public static Book getEmptyBook() {
    Book empty = new Book("","",0);
    empty.setId("");
    return empty;
  }
}
