package com.bookshop.model.category;

import com.bookshop.model.books.Book;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  private Set<CategoryDescription> categoryDescriptions;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  public Category() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<CategoryDescription> getCategoryDescriptions() {
    return categoryDescriptions;
  }

  public void setCategoryDescriptions(
      Set<CategoryDescription> categoryDescriptions) {
    this.categoryDescriptions = categoryDescriptions;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  @Override
  public String toString() {
    return "Category: "
        + "id="
        + id
        + ", categoryDescriptions="
        + categoryDescriptions;
  }
}
