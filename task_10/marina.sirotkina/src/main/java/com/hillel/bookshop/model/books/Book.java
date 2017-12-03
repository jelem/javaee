package com.hillel.bookshop.model.books;

import com.hillel.bookshop.model.category.Category;
import com.hillel.bookshop.model.illustrations.Illustration;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "price")
  private BigDecimal price;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "book_description_id")
  private BookDescription bookDescription;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
  private Set<Illustration> bookIllustrations;

  @Column(name = "isbn")
  private String isbn;

  public Book() {
  }

  public Book(BigDecimal price, Category category,
      BookDescription bookDescription,
      Set<Illustration> bookIllustrations, String isbn) {
    this.price = price;
    this.category = category;
    this.bookDescription = bookDescription;
    this.bookIllustrations = bookIllustrations;
    this.isbn = isbn;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public BookDescription getBookDescription() {
    return bookDescription;
  }

  public void setBookDescription(BookDescription bookDescription) {
    this.bookDescription = bookDescription;
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
        + ", category="
        + category
        + ", bookDescription="
        + bookDescription
        + ", bookIllustrations="
        + bookIllustrations
        + ", isbn='"
        + isbn;
  }
}
