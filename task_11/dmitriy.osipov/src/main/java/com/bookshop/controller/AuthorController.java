package com.bookshop.controller;

import com.bookshop.exception.AuthorException;
import com.bookshop.exception.AuthorNotFoundException;
import com.bookshop.model.Author;
import com.bookshop.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Author> getAll() {
    return authorService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Author get(@PathVariable(name = "id") long id) throws AuthorNotFoundException {
    return authorService.get(id);
  }

  @RequestMapping(value = "/byname", method = RequestMethod.GET)
  public List<Author> getByName(@RequestParam(name = "value") String name) {
    return authorService.getAuthors(name);
  }

  @RequestMapping(value = "/bynames", method = RequestMethod.GET)
  public List<Author> getByNames(@RequestBody List<String> names) {
    return authorService.getAuthors(names);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Author add(@RequestBody Author author) throws AuthorException {
    return authorService.addNew(author);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Author update(@RequestBody Author author) throws AuthorException {
    return authorService.update(author);
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
  public boolean delete(@PathVariable(name = "id") long id) throws AuthorException {
    return authorService.delete(id);
  }
}
