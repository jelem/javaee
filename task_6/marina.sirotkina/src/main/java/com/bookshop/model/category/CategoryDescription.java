package com.bookshop.model.category;

import com.bookshop.model.description.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category_description")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryDescription extends Description {

  @Column(name = "category_title")
  private String categoryTitle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;


}
