package com.hillel.bookshop.model.books;

import com.hillel.bookshop.model.description.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({@NamedQuery(
    name = "bookDescriptionByLanguage",
    query = "from BookDescription bd where bd.language = :language"),
    @NamedQuery(name = "bookDescriptionByAuthor",
        query = "from BookDescription bd where bd.bookAuthor = :book_author")})
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "bookDescriptions",
        query = "SELECT * FROM book_description")})
@Entity
@Table(name = "book_description")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookDescription extends Description {

  @Column(name = "book_title")
  private String bookTitle;

  @Column(name = "book_author")
  private String bookAuthor;

  @Column(name = "publishing_year")
  private String bookPublishingYear;

  public BookDescription() {
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  public String getBookPublishingYear() {
    return bookPublishingYear;
  }

  public void setBookPublishingYear(String bookPublishingYear) {
    this.bookPublishingYear = bookPublishingYear;
  }

  @Override
  public String toString() {
    return "BookDescription: "
        + "bookTitle='"
        + bookTitle
        + ", bookAuthor='"
        + bookAuthor
        + ", bookPublishingYear='"
        + bookPublishingYear;
  }
}
