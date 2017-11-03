package com.bookshop.repository;

import com.bookshop.model.Order;
import com.bookshop.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findAllByUser(User user);
  List<Order> findAllByDone(boolean isDone);
}
