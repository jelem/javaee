package com.bookshop.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookshop.exception.GenreAlreadyExistsException;
import com.bookshop.model.Genre;
import com.bookshop.service.GenreService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {

  @InjectMocks
  private GenreController genreController;

  @Mock
  private GenreService genreService;

  @Test
  public void testGetGenre() throws Exception {
    Genre genre = new Genre(1L, "test");
    when(genreService.get(1L)).thenReturn(genre);
    Genre genre1 = genreController.get(1L);
    verify(genreService).get(1L);
    assertThat(genre1.getId(), is(1L));
  }

  private List<Genre> getGenresList() {
    List<Genre> genres = new ArrayList<>();
    for (int id = 1; id <= 10; id++) {
      genres.add(new Genre(id, "genre_" + id));
    }
    return genres;
  }

  @Test
  public void testGetByName() throws Exception {
    List<Genre> genres = getGenresList();
    when(genreService.findByName("genre")).thenReturn(genres);
    List<Genre> genreList = genreController.getByName("genre");
    verify(genreService).findByName("genre");
    assertThat(genreList, is(genres));
  }

  @Test
  public void testGetAll() throws Exception {
    List<Genre> genres = getGenresList();
    when(genreService.getAll()).thenReturn(genres);
    List<Genre> genreList = genreController.getAll();
    verify(genreService).getAll();
    assertThat(genreList, is(genres));
  }

  @Test
  public void testDelete() throws Exception {
    when(genreService.delete(1L)).thenReturn(true);
    genreController.delete(1L);
    verify(genreService, times(1)).delete(1L);
  }

  @Test
  public void testAdd() throws Exception {
    Genre genre = new Genre(1,"test");
    Genre genreAdded = new Genre();
    BeanUtils.copyProperties(genre, genreAdded);
    genreAdded.setId(1L);
    when(genreService.addNew(genre)).thenReturn(genreAdded);
    Genre added = genreService.addNew(genre);
    verify(genreService, times(1)).addNew(genre);
    assertThat(added.getId(), is(1L));
    assertThat(added, is(genreAdded));
  }

  @Test(expected = GenreAlreadyExistsException.class)
  public void testAddThrowsExc() throws Exception {
    when(genreService.addNew(any(Genre.class))).thenThrow(GenreAlreadyExistsException.class);
    genreController.add(any(Genre.class));
    verify(genreService, times(1)).addNew(any(Genre.class));
  }

  @Test
  public void testUpdate() throws Exception {
    Genre genre = new Genre(1,"test");
    when(genreService.update(genre)).thenReturn(genre);
    Genre updated = genreController.update(genre);
    verify(genreService, times(1)).update(genre);
    assertThat(updated, is(genre));
  }
}
