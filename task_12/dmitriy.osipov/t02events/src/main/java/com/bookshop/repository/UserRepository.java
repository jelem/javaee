package com.bookshop.repository;

import com.bookshop.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByLogin(String login);

}
