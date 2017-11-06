package com.bookshop.repository;

import com.bookshop.model.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

  List<OrderEntity> findAllByOrder_Id(long orderId);

  void deleteByOrderId(long orderId);

}
