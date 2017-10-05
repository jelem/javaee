package com.task.bookshop.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.model.Book;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookRepositoryFileTest {

  private BookRepositoryFile bookRepository;

  @Before
  public void setUp() {
    ArrayList<Book> bookList = new ArrayList<>();
    bookList.add(new Book(1L, "Code Complete", "Steve McConnell", 2004));
    bookList.add(new Book(2L, "The Unified Software Development Process",
        "Grady Booch, Ivar Jacobson, James Rumbaugh", 1995));
    bookList.add(new Book(3L, "Java 8. The Complete Reference, 9th Edition",
        "Herbert Schildt", 2015));
    bookList.add(new Book(4L, new String(("Java. Библиотека профессионала. Том 1. Основы")
        .getBytes(Charset.defaultCharset()), Charset.forName("UTF-8")),
        new String(("Хорстманн К., Корнелл Г.").getBytes(Charset.defaultCharset()),
            Charset.forName("UTF-8")), 2016));
    bookList.add(new Book(5L, "The Lord of the Rings", "J.R.R. Tolkien", 2017));
    bookList.add(new Book(6L, "The Hobbit", "J.R.R. Tolkien", 2016));
    bookList.add(new Book(7L, "The Chronicles of Amber", "Roger Zelazny", 2016));
    bookList.add(new Book(8L, "The Steel Rat", "Harry Harrison", 2015));
    bookList.add(new Book(9L, "The Steel Rat", "Harry Harrison", 2017));

    bookRepository = new BookRepositoryFile();
    bookRepository.setBookList(bookList);

  }

  @Test
  public void bookRepositoryShouldNotBeEmpty() {
    assertThat(bookRepository.isEmpty(), is(false));
  }

  @Test
  public void bookRepositoryGetByNullIdShouldReturnEmptyBook() {
    assertThat(bookRepository.getById(null), is(Book.getEmptyBook()));
  }

  @Test
  public void bookRepositoryFindAllShouldReturn9Books() {
    assertThat(bookRepository.findAll().size(), is(9));
  }

  @Test
  public void bookFindAllByAuthorLikeTolkienShouldReturn2Books() {
    assertThat(bookRepository.findAllByAuthorLike("Tolkien").size(), is(2));
  }

  @Test
  public void bookRepositoryFindAllByYear2016ShouldReturn3Books() {
    assertThat(bookRepository.findAllByYear(2016).size(), is(3));
  }

  @Test
  public void bookRepositoryFindAllByTitleLikeSteelShouldReturn2Books() {
    assertThat(bookRepository.findAllByTitleLike("steel").size(), is(2));
  }

  @Test(expected = BookAlreadyExistsException.class)
  public void bookRepositoryShouldThrowAnBookAlreadyException() {
    bookRepository.saveForTest(new Book("Code Complete", "Steve McConnell", 2004));
  }

  @Test
  public void bookRepositoryShouldAddABook() {
    Book newBook = new Book(10L, "title", "author", 2017);
    int repoSize = bookRepository.findAll().size();
    bookRepository.saveForTest(newBook);
    assertThat(bookRepository.findAll().size(), is(repoSize + 1));
  }

  @Test
  public void bookRepositoryShouldUpdateABook() {
    Book forUpdate = bookRepository.findAll().get(0);
    Book test = new Book("title", "author", 2001);
    Long id = forUpdate.getId();
    forUpdate.setYear(2001);
    forUpdate.setAuthor("author");
    forUpdate.setTitle("title");
    bookRepository.update(forUpdate);
    assertEquals(bookRepository.getById(id), test);
  }

  @Test(expected = BookNotExistsException.class)
  public void bookRepositoryShouldThrowBookNotExistExceptionWhileUpdate() {
    bookRepository.update(new Book("abc", "cba", 1900));
  }
}
