package com.bookshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_orders",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "order_id")
  )
  private User user;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
  private List<OrderEntity> entities;

  @Column(name = "amount")
  private double amount;

  @Column(name = "contacts")
  private String contacts;

  @Column(name = "note")
  private String note;

  @Column(name = "accepted")
  private boolean accepted;

  @Column(name = "done")
  private boolean done;

  public Order(User user, List<OrderEntity> entities, double amount, String contacts,
      String note, boolean done, boolean accepted) {
    this.user = user;
    this.entities = entities;
    this.amount = amount;
    this.contacts = contacts;
    this.note = note;
    this.done = done;
    this.accepted = accepted;
  }

  public Order() {
    this(new User(), new ArrayList<>(), 0, null, null, false, false);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<OrderEntity> getEntities() {
    return entities;
  }

  public void setEntities(List<OrderEntity> entities) {
    this.entities = entities;
  }

  public double getAmount() {
    this.calculateAmount();
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public boolean isAccepted() {
    return accepted;
  }

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  private void calculateAmount() {
    this.amount = 0;
    for (OrderEntity entity : this.entities) {
      amount += entity.getBook().getPrice() * entity.getCount();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Order that = (Order) obj;

    return Objects.equals(this.amount, that.amount)
        && Objects.equals(this.contacts, that.contacts)
        && Objects.equals(this.user, that.user)
        && this.entities.containsAll(that.entities) && that.entities.containsAll(this.entities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, contacts, user, entities);
  }
}
