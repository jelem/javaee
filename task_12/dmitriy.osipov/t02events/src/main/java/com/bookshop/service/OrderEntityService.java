package com.bookshop.service;

import com.bookshop.model.Order;
import com.bookshop.model.OrderEntity;
import com.bookshop.repository.OrderEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class OrderEntityService {

  @Autowired
  private OrderEntityRepository entityRepository;

  public List<OrderEntity> getByOrder(long orderId) {
    return entityRepository.findAllByOrder_Id(orderId);
  }

  @Transactional
  public List<OrderEntity> updateEntities(Order order) {
    entityRepository.deleteByOrderId(order.getId());
    for (OrderEntity entity : order.getEntities()) {
      entity.setOrder(order);
    }
    return entityRepository.save(order.getEntities());
  }

  public List<OrderEntity> addNew(Order order) {
    order.getEntities().forEach(entity -> {
          entity.setId(0);
          entity.setOrder(order);
        }
    );
    return entityRepository.save(order.getEntities());
  }

  public boolean deleteByOrder(long orderId) {
    entityRepository.deleteByOrderId(orderId);
    return this.getByOrder(orderId).isEmpty();
  }
}
