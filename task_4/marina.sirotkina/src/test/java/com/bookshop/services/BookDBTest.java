package com.bookshop.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.bookshop.entity.Book;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookDBTest {

  private Book book;

  @Before
  public void setUp() throws Exception {
    book = new Book("Портрет Дориана Грея", "Оскар Уайльд", "2016");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldUpdateBookListWithNewBook() throws Exception {
    BookDB.INSTANCE.updateBookList(book);
    List<Book> bookList = BookDB.INSTANCE.getBookList();
    System.out.println(bookList);

    assertNotNull(bookList);
    assertEquals("Оскар Уайльд", bookList.get(3).getAuthor());
  }

  @Test
  public void shouldSearchBook() throws Exception {
    List<Book> books = BookDB.INSTANCE.getBookList();

    assertNotNull(books);
    assertEquals("The Martian", books.get(0).getTitle());
  }


}