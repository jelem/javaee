package com.bookshop.controller;

import com.bookshop.exception.UserException;
import com.bookshop.model.User;
import com.bookshop.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<User> getAll() {
    return userService.getAll();
  }

  @RequestMapping(value = "/bylogin", method = RequestMethod.GET)
  public User getByLogin(@RequestParam(name = "value") String login) throws UserException {
    return userService.getByLogin(login);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public User addNew(@RequestBody User user) throws UserException {
    return userService.addNew(user);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public User update(@RequestBody User user) throws UserException {
    return userService.update(user);
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
  public boolean delete(@PathVariable(name = "id") long id) throws UserException {
    return userService.delete(id);
  }
}
