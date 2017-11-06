package com.bookshop.model;

import com.bookshop.exception.UserInvalidException;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private Role role;

  public User(String login, String password, Role role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public User(String login, String password) {
    this(login, password, Role.CUSTOMER);
  }

  public User() {
    this("None", "", Role.CUSTOMER);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public void checkUserFields() throws UserInvalidException {
    if ((login == null || login.isEmpty())
        || (password == null || password.isEmpty())) {
      throw new UserInvalidException();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    User that = (User) obj;

    return Objects.equals(this.login, that.login)
        && Objects.equals(this.password, that.password)
        && Objects.equals(this.role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, role);
  }

  @Override
  public String toString() {
    return "User{"
        + "id=" + id
        + ", login='" + login + '\''
        + ", password='" + password + '\''
        + '}';
  }
}
