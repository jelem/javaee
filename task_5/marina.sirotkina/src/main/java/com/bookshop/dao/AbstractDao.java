package com.bookshop.dao;

import com.bookshop.domain.Entity;
import com.bookshop.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {

  public T getById(int id) {
    String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
    ResultSet rs = null;
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
      ps.setInt(1, id);
      rs = ps.executeQuery();
      while (rs.next()) {
        return createEntityFromRS(rs);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return null;
  }

  public void save(T ob) {
    String query = getSaveQuery();
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
      prepareSaveInsertQuery(ps, ob);
      ps.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public void update(T ob) {
    String query = getUpdateQuery();
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
      prepareUpdateInsertQuery(ps, ob);
      ps.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public void deleteById(int id) {
    String query = "DELETE FROM " + getTableName() + " WHERE id=?";
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public List<T> getAll() {
    String query = "SELECT * FROM " + getTableName();
    List<T> list = new LinkedList<>();
    ResultSet rs = null;
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
      rs = ps.executeQuery();
      while (rs.next()) {
        list.add(createEntityFromRS(rs));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return list;
  }

  protected abstract String getTableName();

  protected abstract T createEntityFromRS(ResultSet rs) throws SQLException;

  protected abstract String getSaveQuery();

  protected abstract String getUpdateQuery();

  protected abstract void prepareSaveInsertQuery(PreparedStatement ps, T ob) throws SQLException;

  protected abstract void prepareUpdateInsertQuery(PreparedStatement ps, T ob) throws SQLException;

  protected Connection getConnection() {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    return connectionUtil.getConnection();
  }

}
