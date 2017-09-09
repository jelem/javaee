package com.zinovatnaya;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BookTest {
  Book book;

  @Rule
  public ExpectedException expectedException =
    ExpectedException.none();

  @Before
  public void setUp() {
    book = new Book ("book", "author", "2017");
  }

  @Test
  public void booksIsCreated() {
    assertNotEquals(null, book);
  }

  @Test
  public void booksIsInitialized() {
    assertEquals("book", book.getName());
    assertEquals("author", book.getAuthor());
    assertEquals("2017", book.getYear());
  }

  @Test
  public void equalBooksAreComparedCorrectly() {
    Book tempBook = new Book ("book", "author", "2017");

    assertTrue(book.equals(tempBook));
  }

  @Test
  public void notEqualBooksAreComparedCorrectly() {
    Book tempBook1 = new Book ("book1", "author", "2017");
    Book tempBook2 = new Book ("book", "author1", "2017");
    Book tempBook3 = new Book ("book", "author", "1999");

    assertFalse(book.equals(tempBook1));
    assertFalse(book.equals(tempBook2));
    assertFalse(book.equals(tempBook3));
  }
}
