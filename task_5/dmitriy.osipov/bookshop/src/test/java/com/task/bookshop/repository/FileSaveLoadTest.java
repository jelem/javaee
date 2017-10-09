package com.task.bookshop.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.task.bookshop.model.Book;

import org.junit.Test;

import java.io.File;

public class FileSaveLoadTest {

  private File getResourceFile(String filename) {
    return new File(getClass().getClassLoader()
        .getResource(filename).getFile());
  }

  @Test
  public void bookRepositoryShouldLoad18Books() {
    BookRepository bookRepository = new BookRepositoryFile(
        getResourceFile("bookListForLoad.txt"));
    assertThat(bookRepository.findAll().size(), is(18));
  }

  @Test
  public void bookRepositoryShouldSaveABook() {
    BookRepository bookRepository = new BookRepositoryFile(
        getResourceFile("bookListForSave.txt"));
    int oldSize = bookRepository.findAll().size();
    bookRepository.save(new Book("title", "author", 2017));

    BookRepository bookRepository1 = new BookRepositoryFile(
        getResourceFile("bookListForSave.txt"));
    assertThat(bookRepository1.findAll().size(), is(oldSize + 1));
  }
}
