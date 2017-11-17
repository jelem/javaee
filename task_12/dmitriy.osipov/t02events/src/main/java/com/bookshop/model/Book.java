package com.bookshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_authors",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authors;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_genres",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private List<Genre> genres;

  @Column(name = "published_year")
  private int year;

  @Column(name = "price")
  private double price;

  public Book() {
    this("None", "None", new ArrayList<>(), new ArrayList<>(), 0, 0);
  }

  public Book(String title, String description, List<Author> authors, List<Genre> genres, int year, double price) {
    this.title = title;
    this.description = description;
    this.authors = authors;
    this.genres = genres;
    this.year = year;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Book another = (Book) obj;

    return Objects.equals(this.price, another.price)
        && Objects.equals(this.year, another.year)
        && Objects.equals(this.title, another.title)
        && this.authors.containsAll(another.authors) && another.authors.containsAll(this.authors)
        && this.genres.containsAll(another.genres) && another.genres.containsAll(this.genres);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.authors, this.genres, this.price, this.title, this.year);
  }
}
