package com.bookshop.model.books;

import com.bookshop.model.category.Category;
import com.bookshop.model.illustrations.Illustration;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<Category> categories;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<BookDescription> bookDescriptions;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<Illustration> bookIllustrations;

  @Column(name = "isbn")
  private String isbn;


}
