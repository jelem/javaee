package com.bookshop.dao;

import com.bookshop.domain.Book;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao extends AbstractDao<Book> implements Serializable {

  @Override
  protected String getTableName() {
    return "books";
  }

  @Override
  protected Book createEntityFromRS(ResultSet rs) throws SQLException {
    return new Book(rs.getLong("id"), rs.getString("title"),
        rs.getString("author"), rs.getString("description"),
        rs.getBlob("illustration"), rs.getBigDecimal("price"));
  }

  @Override
  protected String getSaveQuery() {
    return "INSERT INTO books (title, author, description, illustration, price) VALUES(?, ?, ?, ?, ?)";
  }

  @Override
  protected String getUpdateQuery() {
    return "UPDATE books SET title=?, author=?, description=?, illustration=?, price=? WHERE id=?";
  }

  @Override
  protected void prepareSaveInsertQuery(PreparedStatement ps, Book book) throws SQLException {
    ps.setString(1, book.getTitle());
    ps.setString(2, book.getAuthor());
    ps.setCharacterStream(3, new StringReader(book.getDescription()));
    ps.setBlob(4, book.getIllustration());
    ps.setBigDecimal(5, book.getPrice());
  }

  @Override
  protected void prepareUpdateInsertQuery(PreparedStatement ps, Book book) throws SQLException {
    ps.setString(1, book.getTitle());
    ps.setString(2, book.getAuthor());
    ps.setString(3, book.getDescription());
    ps.setBlob(4, book.getIllustration());
    ps.setBigDecimal(5, book.getPrice());
    ps.setLong(6, book.getId());
  }
}
