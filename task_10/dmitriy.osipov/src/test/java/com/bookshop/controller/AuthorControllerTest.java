package com.bookshop.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookshop.exception.AuthorAlreadyExistsException;
import com.bookshop.model.Author;
import com.bookshop.service.AuthorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {

  @InjectMocks
  private AuthorController authorController;

  @Mock
  private AuthorService authorService;

  @Test
  public void testGetAuthor() throws Exception {
    Author author = new Author();
    author.setId(1L);
    when(authorService.get(1L)).thenReturn(author);
    Author author1 = authorController.get(1L);
    verify(authorService).get(1L);
    assertThat(author1.getId(), is(1L));
  }

  private List<Author> getAuthorsList() {
    List<Author> authors = new ArrayList<>();
    for (int id = 1; id <= 10; id++) {
      authors.add(new Author("author_" + id));
      authors.get(id - 1).setId(id);
    }
    return authors;
  }

  @Test
  public void testGetByName() throws Exception {
    List<Author> authors = getAuthorsList();
    when(authorService.getAuthors("author")).thenReturn(authors);
    List<Author> authorList = authorController.getByName("author");
    verify(authorService).getAuthors("author");
    assertThat(authorList, is(authors));
  }

  public void testGetByNames() throws Exception {
    List<Author> authors = getAuthorsList();
    List<String> names = new ArrayList<>();
    for (int id = 1; id <= 10; id++) {
      names.add("author_" + id);
    }
    when(authorService.getAuthors(names)).thenReturn(authors);
    List<Author> authorList = authorController.getByNames(names);
    verify(authorService).getAuthors(names);
    assertThat(authorList, is(authors));
  }

  @Test
  public void testGetAll() throws Exception {
    List<Author> authors = getAuthorsList();
    when(authorService.getAll()).thenReturn(authors);
    List<Author> authorList = authorController.getAll();
    verify(authorService).getAll();
    assertThat(authorList, is(authors));
  }

  @Test
  public void testDelete() throws Exception {
    when(authorService.delete(1L)).thenReturn(true);
    authorController.delete(1L);
    verify(authorService, times(1)).delete(1L);
  }

  @Test
  public void testAdd() throws Exception {
    Author author = new Author("test");
    Author authorAdded = new Author();
    BeanUtils.copyProperties(author, authorAdded);
    authorAdded.setId(1L);
    when(authorService.addNew(author)).thenReturn(authorAdded);
    Author added = authorService.addNew(author);
    verify(authorService, times(1)).addNew(author);
    assertThat(added.getId(), is(1L));
    assertThat(added, is(authorAdded));
  }

  @Test(expected = AuthorAlreadyExistsException.class)
  public void testAddThrowsExc() throws Exception {
    when(authorService.addNew(any(Author.class))).thenThrow(AuthorAlreadyExistsException.class);
    authorController.add(any(Author.class));
    verify(authorService, times(1)).addNew(any(Author.class));
  }

  @Test
  public void testUpdate() throws Exception {
    Author author = new Author("test");
    when(authorService.update(author)).thenReturn(author);
    Author updated = authorController.update(author);
    verify(authorService, times(1)).update(author);
    assertThat(updated, is(author));
  }
}
