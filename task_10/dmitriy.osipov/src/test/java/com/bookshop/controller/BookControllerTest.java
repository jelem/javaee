package com.bookshop.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookshop.exception.BookAlreadyExistsException;
import com.bookshop.model.Author;
import com.bookshop.model.Book;
import com.bookshop.model.Genre;
import com.bookshop.service.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Test
  public void testGetBook() throws Exception {
    Book book = getBook();
    when(bookService.get(1L)).thenReturn(book);
    Book book1 = bookController.get(1L);
    verify(bookService).get(1L);
    assertThat(book1.getId(), is(1L));
  }

  private Book getBook() {
    List<Author> authors = new ArrayList<>(Arrays.asList(
        new Author("author_1"),
        new Author("author_2")
    ));
    List<Genre> genres = new ArrayList<>(Arrays.asList(
        new Genre(1, "genre_1"),
        new Genre(2, "genre_2")
    ));
    Book book = new Book("title", "descr", authors, genres, 2017, 15.50);
    book.setId(1L);
    return book;
  }

  private List<Book> getBooksList() {
    List<Book> books = new ArrayList<>();
    for (int id = 1; id <= 10; id++) {
      books.add(getBook());
      books.get(id - 1).setId(id);
      books.get(id - 1).setTitle("title_" + id);
    }
    return books;
  }

  @Test
  public void testGetByTitle() throws Exception {
    List<Book> books = getBooksList();
    when(bookService.getByTitle("title")).thenReturn(books);
    List<Book> bookList = bookController.findByTitle("title");
    verify(bookService).getByTitle("title");
    assertThat(bookList, is(books));
  }

  @Test
  public void testGetAll() throws Exception {
    List<Book> books = getBooksList();
    when(bookService.getAll()).thenReturn(books);
    List<Book> bookList = bookController.getAll();
    verify(bookService).getAll();
    assertThat(bookList, is(books));
  }

  @Test
  public void testDelete() throws Exception {
    when(bookService.delete(1L)).thenReturn(true);
    bookService.delete(1L);
    verify(bookService, times(1)).delete(1L);
  }

  @Test
  public void testAdd() throws Exception {
    Book book = getBook();
    Book bookAdded = new Book();
    BeanUtils.copyProperties(book, bookAdded);
    bookAdded.setId(1L);
    when(bookService.addNew(book)).thenReturn(bookAdded);
    Book added = bookController.add(book);
    verify(bookService, times(1)).addNew(book);
    assertThat(added.getId(), is(1L));
    assertThat(added, is(bookAdded));
  }

  @Test(expected = BookAlreadyExistsException.class)
  public void testAddThrowsExc() throws Exception {
    when(bookService.addNew(any(Book.class))).thenThrow(BookAlreadyExistsException.class);
    bookController.add(any(Book.class));
    verify(bookService, times(1)).addNew(any(Book.class));
  }

  @Test
  public void testUpdate() throws Exception {
    Book book = getBook();
    when(bookService.update(book)).thenReturn(book);
    Book updated = bookController.update(book);
    verify(bookService, times(1)).update(book);
    assertThat(updated, is(book));
  }
}
