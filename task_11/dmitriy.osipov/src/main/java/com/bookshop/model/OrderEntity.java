package com.bookshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private Order order;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_id")
  private Book book;

  @Column(name = "quantity")
  private int count;

  public OrderEntity(Book book, int count) {
    this.book = book;
    this.count = count;
  }

  public OrderEntity() {
    this(null, 0);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    OrderEntity that = (OrderEntity) obj;

    return Objects.equals(this.book, that.book) && Objects.equals(this.order, that.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(book, order);
  }
}
