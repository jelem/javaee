package com.bookshop.model.books;

import com.bookshop.model.category.Category;
import com.bookshop.model.illustrations.Illustration;

import java.math.BigDecimal;
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

  @Column(name = "price")
  private BigDecimal price;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<Category> categories;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<BookDescription> bookDescriptions;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
  private Set<Illustration> bookIllustrations;

  @Column(name = "isbn")
  private String isbn;

  public Book() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public Set<BookDescription> getBookDescriptions() {
    return bookDescriptions;
  }

  public void setBookDescriptions(Set<BookDescription> bookDescriptions) {
    this.bookDescriptions = bookDescriptions;
  }

  public Set<Illustration> getBookIllustrations() {
    return bookIllustrations;
  }

  public void setBookIllustrations(
      Set<Illustration> bookIllustrations) {
    this.bookIllustrations = bookIllustrations;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return "Book: "
        + "id="
        + id
        + ", price='"
        + price
        + ", categories="
        + categories
        + ", bookDescriptions="
        + bookDescriptions
        + ", bookIllustrations="
        + bookIllustrations
        + ", isbn='"
        + isbn;
  }
}
