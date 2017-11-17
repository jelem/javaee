package com.bookshop.repository;

import com.bookshop.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

  Set<UserRole> findByUserId(Long userId);

  UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}
