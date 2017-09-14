package com.zinovatnaya;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BooksDBTest {

  @Rule
  public ExpectedException expectedException =
    ExpectedException.none();

  @Before
  public void setUp() {
    BooksDB.clear();
    BooksDB.add(new Book("book1", "author1", "2016"));
    BooksDB.add(new Book("book2", "author2", "2000"));
    BooksDB.add(new Book("book3", "author3", "1997"));
  }

  @Test
  public void booksDBcannotBeNull() {
    assertNotEquals(null, BooksDB.getBooks());
  }

  @Test
  public void booksDBstoresThreeSetUpBooks() {
    assertEquals(3, BooksDB.getBooks().size());
  }

  @Test
  public void booksDBstoresCorrectBooks() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("book1", "author1", "2016"));
    books.add(new Book("book2", "author2", "2000"));
    books.add(new Book("book3", "author3", "1997"));

    assertTrue(books.get(0).equals(BooksDB.getBooks().get(0)));
    assertTrue(books.get(1).equals(BooksDB.getBooks().get(1)));
    assertTrue(books.get(2).equals(BooksDB.getBooks().get(2)));
  }

  @Test
  public void booksDBaddsNewBook() {
    Book book = new Book("book4", "author4", "1950");
    BooksDB.add(book);

    assertTrue(BooksDB.getBooks().contains(book));
  }

  @Test
  public void booksDBclearsDB() {
    Book book = new Book("book5", "author5", "1990");
    BooksDB.add(book);
    BooksDB.clear();

    assertEquals(0, BooksDB.getBooks().size());
  }
}
