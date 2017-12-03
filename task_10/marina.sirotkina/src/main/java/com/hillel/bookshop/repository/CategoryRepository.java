package com.hillel.bookshop.repository;

import com.hillel.bookshop.model.category.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
