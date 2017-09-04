package com.task.bookshop.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class BookTest {

  @Test
  public void emptyBookShouldReturnEmptyId() {
    assertThat(Book.getEmptyBook().getId(), is(""));
  }

  @Test
  public void emptyBookShouldReturnEmptyTitle() {
    assertThat(Book.getEmptyBook().getTitle(), is(""));
  }

  @Test
  public void emptyBookShouldReturnEmptyAuthor() {
    assertThat(Book.getEmptyBook().getAuthor(), is(""));
  }

  @Test
  public void emptyBookShouldReturnYearZero() {
    assertThat(Book.getEmptyBook().getYear(), is(0));
  }

  @Test
  public void booksWithTheSameYearTitleAuthorShouldEquals() {
    Book book1 = new Book("title", "author", 2017);
    Book book2 = new Book("title", "author", 2017);
    assertThat(book1.equals(book2), is(true));
  }

  @Test
  public void booksWithTheDifferentTitleShouldBeDifferent() {
    Book book1 = new Book("title1", "author", 2017);
    Book book2 = new Book("title2", "author", 2017);
    assertThat(book1.equals(book2), is(false));
  }

  @Test
  public void booksWithTheDifferentAuthorsShouldBeDifferent() {
    Book book1 = new Book("title", "author1", 2017);
    Book book2 = new Book("title", "author2", 2017);
    assertThat(book1.equals(book2), is(false));
  }

  @Test
  public void booksWithTheSameAuthorsInDifferentOrderShouldEquals() {
    Book book1 = new Book("title", "author1, author2", 2017);
    Book book2 = new Book("title", "author2, author1", 2017);
    assertThat(book1.equals(book2), is(true));
  }

  @Test
  public void newBooksShouldHaveDifferentId() {
    Book book1 = new Book("title1", "author1", 2015);
    Book book2 = new Book("title2", "author2", 2017);
    assertThat(book1.getId().equals(book2.getId()), is(false));
  }

}
