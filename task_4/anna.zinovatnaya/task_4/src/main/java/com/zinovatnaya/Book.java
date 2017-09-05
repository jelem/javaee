package com.zinovatnaya;

public class Book {

  public Book(String name, String author, String year) {
    this.name = name;
    this.author = author;
    this.year = year;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getYear() {
    return year;
  }

  public boolean equals(Book book) {
    boolean equals = false;
    if(this.name.equals(book.getName()))
      if(this.author.equals(book.getAuthor()))
        if(this.year.equals(book.getYear()))
          equals = true;
    return equals;
  }

  private String name;
  private String author;
  private String year;
}