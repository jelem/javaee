package com.task.bookshop.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {

  private static final long serialVersionUID = 170820000000L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "published")
  private int year;

  @Column(name = "image")
  private Blob image;

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

  public Blob getImage() {
    return image;
  }

  public void setImage(Blob image) {
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
