package com.bookshop.controller;

import com.bookshop.exception.GenreException;
import com.bookshop.exception.GenreNotFoundException;
import com.bookshop.model.Genre;
import com.bookshop.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

  @Autowired
  private GenreService genreService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Genre> getAll() {
    return genreService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Genre get(@PathVariable(name = "id") long id) throws GenreNotFoundException {
    return genreService.get(id);
  }

  @RequestMapping(value = "/byname", method = RequestMethod.GET)
  public List<Genre> getByName(@RequestParam(name = "value") String name) {
    return genreService.findByName(name);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Genre add(@RequestBody Genre genre) throws GenreException {
    return genreService.addNew(genre);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Genre update(@RequestBody Genre genre) throws GenreException {
    return genreService.update(genre);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public boolean delete(@PathVariable(name = "id") long id) throws GenreNotFoundException {
    return genreService.delete(id);
  }
}
