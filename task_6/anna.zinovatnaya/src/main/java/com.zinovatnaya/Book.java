package com.zinovatnaya;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private Author author;

  @Column(name = "description")
  private String description;

  @Column(name = "image_path")
  private String imagePath;

  public Book(String name, Author author, String description, String imagePath) {
    this.name = name;
    this.author = author;
    this.description = description;
    this.imagePath = imagePath;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public boolean equalsBook(Book book) {
    boolean equals = false;
    if (this.name.equals(book.getName())) {
      if (this.author.equalsAuthor(book.getAuthor())) {
        if (this.description.equals(book.getDescription())) {
          if (this.imagePath.equals(book.getImagePath())) {
            equals = true;
          }
        }
      }
    }
    return equals;
  }

  @Override
  public String toString() {
    return "Book{"
           + "id=" + id 
           + ", name='" + name + '\'' 
           + ", author=" + author 
           + ", description='" + description + '\'' 
           + ", imagePath='" + imagePath + '\'' 
           + '}';
  }
}