package com.bookshop.model.description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Description {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;

  @Column(name = "description")
  private String description;

  @Column(name = "language")
  private String language;

}
