package com.bookshop.controller;

import com.bookshop.exception.OrderException;
import com.bookshop.model.Order;
import com.bookshop.model.User;
import com.bookshop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
  
  @Autowired
  private OrderService orderService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Order> getAll() {
    return orderService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Order get(@PathVariable(name = "id") long id) throws OrderException {
    return orderService.get(id);
  }

  @RequestMapping(value = "/byuser", method = RequestMethod.GET)
  public List<Order> getByUser(@RequestBody User user) {
    return orderService.getByUser(user);
  }

  @RequestMapping(value = "/bydone", method = RequestMethod.GET)
  public List<Order> getByDone(@RequestParam(name = "value") boolean isDone) {
    return orderService.getByDone(isDone);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Order add(@RequestBody Order order) throws OrderException {
    return orderService.addNew(order);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Order update(@RequestBody Order order) throws OrderException {
    return orderService.update(order);
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
  public boolean delete(@PathVariable(name = "id") long id) throws OrderException {
    return orderService.delete(id);
  }

  @RequestMapping(value = "/buy", method = RequestMethod.POST)
  public Order buy(@RequestBody Order order) throws OrderException {
    return orderService.buy(order);
  }
}
