package com.bookshop.model.books;

import com.bookshop.model.description.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_description")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookDescription extends Description {

  @Column(name = "book_title")
  private String bookTitle;

  @Column(name = "book_author")
  private String bookAuthor;

  @Column(name = "publishing_year")
  private String bookPublishingYear;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;
}
