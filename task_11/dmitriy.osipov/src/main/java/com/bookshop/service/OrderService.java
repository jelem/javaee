package com.bookshop.service;

import com.bookshop.exception.OrderAlreadyExistsException;
import com.bookshop.exception.OrderException;
import com.bookshop.exception.OrderInvalidException;
import com.bookshop.exception.OrderNotFoundException;
import com.bookshop.model.Order;
import com.bookshop.model.User;
import com.bookshop.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderEntityService entityService;

  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  public Order get(long id) throws OrderException {
    Order order = orderRepository.findOne(id);
    if (order == null) {
      throw new OrderNotFoundException();
    }
    return order;
  }

  public List<Order> getByUser(User user) {
    return orderRepository.findAllByUser(user);
  }

  public List<Order> getByDone(boolean isDone) {
    return orderRepository.findAllByDone(isDone);
  }

  @Transactional
  public Order addNew(Order order) throws OrderException {
    if (orderRepository.exists(order.getId())) {
      throw new OrderAlreadyExistsException();
    }

    order.setEntities(entityService.addNew(order));
    return orderRepository.save(order);
  }

  @Transactional
  public Order update(Order order) throws OrderException {
    Order inDb = orderRepository.findOne(order.getId());
    if (inDb == null) {
      throw new OrderNotFoundException();
    }
    if (order.getEntities().isEmpty()) {
      setEntitiesForUpdate(order, inDb);
    }
    if (order.getUser() == null) {
      setUserForUpdate(order, inDb);
    }
    order.setEntities(entityService.updateEntities(order));
    return orderRepository.save(order);
  }

  private void setUserForUpdate(Order order, Order inDb) throws OrderInvalidException {
    if (inDb.getUser() == null) {
      throw new OrderInvalidException("User didn't assign for this order");
    }
    order.setUser(inDb.getUser());
  }

  private void setEntitiesForUpdate(Order order, Order inDb) throws OrderInvalidException {
    if (inDb.getEntities().isEmpty()) {
      throw new OrderInvalidException("Your order is empty!");
    }
    order.setEntities(inDb.getEntities());
  }

  private void checkForDone(Order inDb) throws OrderException {
    if (inDb.isDone()) {
      throw new OrderException("This order already completed!");
    }
  }

  @Transactional
  public boolean delete(long orderId) throws OrderException {
    if (!orderRepository.exists(orderId)) {
      throw new OrderNotFoundException();
    }
    boolean entitiesDeleted = entityService.deleteByOrder(orderId);
    orderRepository.delete(orderId);
    return entitiesDeleted && !orderRepository.exists(orderId);
  }

  @Transactional
  public Order buy(Order order) throws OrderException {
    Order result;
    order.setAccepted(true);
    order.setDone(false);
    Order inDb = orderRepository.findOne(order.getId());
    if (inDb == null) {
      result = this.addNew(order);
    } else {
      checkForDone(inDb);
      result = this.update(order);
    }
    return result;
  }
}
