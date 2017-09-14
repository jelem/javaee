package com.task.bookshop.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setYear(int year) {
    this.year = year;
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
    return author != null ? this.equalsAuthors(book) : book.author == null;
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

  private boolean equalsAuthors(Book otherBook) {
    List<String> authorsThis = Arrays.asList(this.author
        .replace(",","").split(" "));
    List<String> authorsThat = Arrays.asList(otherBook.getAuthor()
        .replace(",","").split(" "));
    return authorsThis.containsAll(authorsThat) && authorsThat.containsAll(authorsThis);
  }
}
