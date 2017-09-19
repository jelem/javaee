package com.bookshop.model.illustrations;

import com.bookshop.model.books.Book;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "illustration")
public class Illustration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "illustration_title")
  private String illustrationTitle;

  @Column(name = "illustrationUrl")
  private String illustrationUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @Transient
  private InputStream inputStream;

  public Illustration() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIllustrationTitle() {
    return illustrationTitle;
  }

  public void setIllustrationTitle(String illustrationTitle) {
    this.illustrationTitle = illustrationTitle;
  }

  public String getIllustrationUrl() {
    return illustrationUrl;
  }

  public void setIllustrationUrl(String illustrationUrl) {
    this.illustrationUrl = illustrationUrl;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public String toString() {
    return "Illustration: "
        + "id="
        + id
        + ", illustrationTitle='"
        + illustrationTitle
        + ", illustrationUrl='"
        + illustrationUrl
        + ", book="
        + book
        + ", inputStream="
        + inputStream;
  }
}
