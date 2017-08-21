package com.greyslon.servlets.models;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

  private String bookName;
  private String author;
  private String publishedYear;

  public Book(String author, String bookName, String publishedYear) {
    this.bookName = bookName;
    this.author = author;
    this.publishedYear = publishedYear;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublishedYear() {
    return publishedYear;
  }

  public void setPublishedYear(String publishedYear) {
    this.publishedYear = publishedYear;
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
    return Objects.equals(bookName, book.bookName)
        && Objects.equals(author, book.author)
        && Objects.equals(publishedYear, book.publishedYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookName, author, publishedYear);
  }

  @Override
  public String toString() {
    return "book: '" + bookName + '\''
        + ", author: '" + author + '\''
        + ", publishedYear: '" + publishedYear + '\'';
  }
}
