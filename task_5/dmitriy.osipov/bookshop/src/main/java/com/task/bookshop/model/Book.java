package com.task.bookshop.model;

import com.task.bookshop.repository.BeansContainer;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {

  private static final long serialVersionUID = 170820000000L;

  private Long id;
  private String title;
  private String author;
  private int year;
  private Object image;

  public Book(String title, String author, int year) {
    this.id = 0L;
    this.title = title;
    this.author = author;
    this.year = year;
    this.image = null;
  }

  public Book(Long id, String title, String author, int year) {
    this(title, author, year);
    this.id = id;
  }

  private Book() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public byte[] getImage() {
    return (byte[]) image;
  }

  public void setImage(Object image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Book that = (Book) obj;

    return (this.year == that.year)
        && Objects.equals(this.title, that.title)
        && this.equalsAuthors(that);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, year);
  }

  public static Book getEmptyBook() {
    Book empty = new Book("", "", 0);
    empty.setId(0L);
    return empty;
  }

  private boolean equalsAuthors(Book otherBook) {
    List<String> authorsThis = Arrays.asList(this.author
        .replace(",", "").split(" "));
    List<String> authorsThat = Arrays.asList(otherBook.getAuthor()
        .replace(",", "").split(" "));
    return authorsThis.containsAll(authorsThat) && authorsThat.containsAll(authorsThis);
  }
}
