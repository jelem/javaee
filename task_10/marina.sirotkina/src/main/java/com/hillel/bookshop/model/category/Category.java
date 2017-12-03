package com.hillel.bookshop.model.category;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "category")
  private Set<CategoryDescription> categoryDescriptions;

  public Category() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<CategoryDescription> getCategoryDescriptions() {
    return categoryDescriptions;
  }

  public void setCategoryDescriptions(
      Set<CategoryDescription> categoryDescriptions) {
    this.categoryDescriptions = categoryDescriptions;
  }

  @Override
  public String toString() {
    return "Category: "
        + "id="
        + id
        + ", categoryDescriptions="
        + categoryDescriptions;
  }
}
