package com.bookshop.model.category;

import com.bookshop.model.description.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({@NamedQuery(
    name = "categoryDescriptionByTitle",
    query = "from CategoryDescription cd where cd.categoryTitle = :category_title")})
@Entity
@Table(name = "category_description")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryDescription extends Description {

  @Column(name = "category_title")
  private String categoryTitle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  public CategoryDescription() {
  }

  public String getCategoryTitle() {
    return categoryTitle;
  }

  public void setCategoryTitle(String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "CategoryDescription: "
        + "categoryTitle='"
        + categoryTitle;
  }
}
