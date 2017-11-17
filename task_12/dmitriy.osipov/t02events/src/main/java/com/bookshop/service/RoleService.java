package com.bookshop.service;

import com.bookshop.model.Role;
import com.bookshop.model.UserRole;
import com.bookshop.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

  @Autowired
  private UserRoleRepository userRoleRepository;

  public Set<UserRole> getUserRoles(long id) {
    return userRoleRepository.findByUserId(id);
  }

  public UserRole save(UserRole userRole) {
    return userRoleRepository.save(userRole);
  }

  public Iterable<UserRole> save(Set<UserRole> userRoles) {
    return userRoleRepository.save(userRoles);
  }

  public void removeRole(long userId, Role userRole) {
    UserRole roleInDb = getUserRole(userId, userRole);
    if (roleInDb != null) {
      userRoleRepository.delete(roleInDb);
    }
  }

  public UserRole getUserRole(long userId, Role role) {
    return userRoleRepository.findByUserIdAndRoleId(userId, role.getId());
  }
}
