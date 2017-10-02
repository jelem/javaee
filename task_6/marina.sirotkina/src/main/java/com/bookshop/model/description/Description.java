package com.bookshop.model.description;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Description {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;

  @Column(name = "description")
  @Type(type = "text")
  private String description;

  @Column(name = "language")
  private String language;

  public Description() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return "Description: "
        + "id="
        + id
        + ", description='"
        + description
        + ", language='"
        + language;
  }
}
