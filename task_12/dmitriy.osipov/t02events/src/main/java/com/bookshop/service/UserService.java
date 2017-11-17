package com.bookshop.service;

import com.bookshop.exception.UserAlreadyExistsException;
import com.bookshop.exception.UserException;
import com.bookshop.exception.UserInvalidException;
import com.bookshop.exception.UserNotFoundException;
import com.bookshop.model.Role;
import com.bookshop.model.User;
import com.bookshop.model.UserRole;
import com.bookshop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User get(long id) throws UserException {
    User user = userRepository.findOne(id);
    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }

  public User getByLogin(String login) throws UserException {
    User user = findByLogin(login);
    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }

  private User findByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  public User addNew(User user) throws UserException {
    user.checkUserFields();
    if (userRepository.exists(user.getId())
        || findByLogin(user.getLogin()) != null) {
      throw new UserAlreadyExistsException();
    }
    long id = user.getId();
    Set<UserRole> userRoles = roleService.getUserRoles(id);
    userRoles.add(new UserRole(id, Role.CUSTOMER));
    roleService.save(userRoles);

    return userRepository.save(user);
  }

  public User update(User user) throws UserException {
    user.checkUserFields();
    if (!userRepository.exists(user.getId())) {
      throw new UserNotFoundException();
    }
    User inDbUserLogin = findByLogin(user.getLogin());
    if (inDbUserLogin != null && inDbUserLogin.getId() != user.getId()) {
      throw new UserInvalidException("You cannot use this login");
    }
    return userRepository.save(user);
  }

  public boolean delete(long id) throws UserException {
    if (!userRepository.exists(id)) {
      throw new UserNotFoundException();
    }
    userRepository.delete(id);
    return !userRepository.exists(id);
  }
}
