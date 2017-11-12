package com.hillel.bookshop.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hillel.bookshop.model.books.Book;
import com.hillel.bookshop.service.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookshopControllerTest {

  @InjectMocks
  private BookshopController bookshopController;

  @Mock
  private BookService bookService;

  @Test
  public void findBooksByAuthor() throws Exception {
    Book book = new Book();
    Book book1 = new Book();
    book.setId(1);
    book1.setId(2);
    List<Book> books = Arrays.asList(book, book1);

    when(bookService.getAllBooksByAuthor("author")).thenReturn(books);
    List<Book> bookList = bookshopController.findBooksByAuthor("author");
    verify(bookService).getAllBooksByAuthor("author");

    assertThat(bookList.get(0).getId(), is(1L));
    assertThat(bookList.get(1).getId(), is(2L));
  }

  @Test
  public void saveNewBook() throws Exception {
    Book book = new Book();
    book.setId(1);
    book.setPrice(BigDecimal.valueOf(10.2));
    book.setIsbn("12345");
    when(bookService.getById(1L)).thenReturn(book);

    bookshopController.saveNewBook(book);
    Book book1 = bookService.getById(1L);
    assertThat(book1.getId(), is(1L));
    assertThat(book1.getPrice(), is(BigDecimal.valueOf(10.2)));
  }

  @Test
  public void updateBook() throws Exception {
  }

  @Test
  public void deleteBook() throws Exception {
  }

}