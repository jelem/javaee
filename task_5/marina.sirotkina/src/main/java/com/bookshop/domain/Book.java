package com.bookshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

public class Book extends Entity implements Serializable {

  private String title;
  private String author;
  private String description;
  private Blob illustration;
  private BigDecimal price;

  public Book(String title, String author, String description, Blob illustration,
      BigDecimal price) {
    this.title = title;
    this.author = author;
    this.description = description;
    this.illustration = illustration;
    this.price = price;
  }

  public Book(Long id, String title, String author, String description, Blob illustration,
      BigDecimal price) {
    setId(id);
    this.title = title;
    this.author = author;
    this.description = description;
    this.illustration = illustration;
    this.price = price;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Blob getIllustration() {
    return illustration;
  }

  public void setIllustration(Blob illustration) {
    this.illustration = illustration;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
