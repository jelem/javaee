package com.task.bookshop.repository;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.exceptions.DBConnectionException;
import com.task.bookshop.model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.PooledConnection;

public class BookRepositoryImpl implements BookRepository {

  private Connection connection;

  public BookRepositoryImpl() {
    createConnection(getProperties(BeansContainer.dbPropertiesFile));
  }

  private Properties getProperties(String propertiesFile) {
    Properties properties;
    try (InputStream inputStream = getClass().getResourceAsStream(propertiesFile)) {
      properties = new Properties();
      properties.load(inputStream);
    } catch (IOException ioExc) {
      ioExc.printStackTrace();
      throw new DBConnectionException("Properties file not found");
    }
    return properties;
  }

  private void createConnection(Properties properties) {
    checkDriver(properties.getProperty("dbDriver"));
    MysqlConnectionPoolDataSource cp = new MysqlConnectionPoolDataSource();
    cp.setUrl(properties.getProperty("dbSourceUrl"));
    cp.setUser(properties.getProperty("dbLogin"));
    cp.setPassword(properties.getProperty("dbPass"));

    try {
      PooledConnection pooledConnection = cp.getPooledConnection();
      connection = pooledConnection.getConnection();
    } catch (SQLException sqlExc) {
      sqlExc.printStackTrace();
      throw new DBConnectionException("Connection cannot be created");
    }
  }

  private void checkDriver(String driverName) {
    try {
      Class.forName(driverName);
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      throw new DBConnectionException("No driver found");
    }
  }

  private void rollBackTransaction() {
    try {
      connection.rollback();
    } catch (SQLException sqlExc) {
      sqlExc.printStackTrace();
      throw new DBConnectionException();
    }
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void save(Book book) throws BookAlreadyExistsException {
    if (exists(book)) {
      throw new BookAlreadyExistsException("Book already exists in the DB");
    }
    try (PreparedStatement preparedStatement = connection
        .prepareStatement("insert into books(title, author, published, image) "
            + "VALUES (?, ?, ?, ?)")) {
      boolean autoCommit;
      autoCommit = connection.getAutoCommit();
      connection.setAutoCommit(false);
      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getAuthor());
      preparedStatement.setInt(3, book.getYear());
      preparedStatement.setBytes(4, book.getImage());
      preparedStatement.execute();
      connection.commit();
      connection.setAutoCommit(autoCommit);
    } catch (Exception exc) {
      rollBackTransaction();
      exc.printStackTrace();
      throw new DBConnectionException();
    }
  }

  @Override
  public void update(Book book) throws BookNotExistsException {
    if (!exists(book)) {
      throw new BookNotExistsException("Book doesn't exist in DB");
    }
    try (PreparedStatement statement = connection.prepareStatement("UPDATE books SET title = ?, "
        + "author = ?, published = ?, image = ? WHERE id = ?")) {
      boolean autoCommit;
      autoCommit = connection.getAutoCommit();
      connection.setAutoCommit(false);
      statement.setString(1, book.getTitle());
      statement.setString(2, book.getAuthor());
      statement.setInt(3, book.getYear());
      statement.setBytes(4, book.getImage());
      statement.setLong(5, book.getId());
      statement.executeUpdate();
      connection.commit();
      connection.setAutoCommit(autoCommit);
      statement.close();
    } catch (Exception exc) {
      rollBackTransaction();
      exc.printStackTrace();
      throw new DBConnectionException();
    }
  }

  @Override
  public List<Book> findAll() {
    List<Book> booksList = new ArrayList<>();
    try (Statement statement = connection.createStatement()) {
      ResultSet dbBooks = statement.executeQuery("SELECT * FROM books");
      while (dbBooks.next()) {
        Book book = getBookFromResultSet(dbBooks);
        booksList.add(book);
      }
      dbBooks.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return booksList;
  }

  @Override
  public Book getById(Long id) {
    Book book = null;
    try (PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM books WHERE id = ?")) {
      statement.setLong(1, id);
      ResultSet dbBooks = statement.executeQuery();
      while (dbBooks.next()) {
        book = getBookFromResultSet(dbBooks);
      }
      dbBooks.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return book;
  }

  @Override
  public List<Book> findAllByAuthorLike(String author) {
    List<Book> booksList = new ArrayList<>();
    try (PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM books WHERE author LIKE ?")) {
      statement.setString(1, "%" + author + "%");
      ResultSet dbBooks = statement.executeQuery();
      while (dbBooks.next()) {
        Book book = getBookFromResultSet(dbBooks);
        booksList.add(book);
      }
      dbBooks.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return booksList;
  }

  private Book getBookFromResultSet(ResultSet dbBooks) throws SQLException {
    Book book = Book.getEmptyBook();
    book.setId(dbBooks.getLong("id"));
    book.setTitle(dbBooks.getString("title"));
    book.setAuthor(dbBooks.getString("author"));
    book.setYear(dbBooks.getInt("published"));
    book.setImage(dbBooks.getBytes("image"));
    return book;
  }

  @Override
  public List<Book> findAllByYear(int year) {
    List<Book> booksList = new ArrayList<>();
    try (PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM books WHERE published = ?")) {
      statement.setInt(1, year);
      ResultSet dbBooks = statement.executeQuery();
      while (dbBooks.next()) {
        Book book = getBookFromResultSet(dbBooks);
        booksList.add(book);
      }
      dbBooks.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return booksList;
  }

  @Override
  public List<Book> findAllByTitleLike(String title) {
    List<Book> booksList = new ArrayList<>();
    try (PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM books WHERE title LIKE ?")) {
      statement.setString(1, "%" + title + "%");
      ResultSet dbBooks = statement.executeQuery();
      while (dbBooks.next()) {
        Book book = getBookFromResultSet(dbBooks);
        booksList.add(book);
      }
      dbBooks.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return booksList;
  }

  @Override
  public boolean exists(Book book) {
    boolean result = false;
    try (PreparedStatement statement = connection
        .prepareStatement("SELECT COUNT(*) as result FROM books WHERE title = ? "
            + "AND author = ? AND published = ?")) {
      statement.setString(1, book.getTitle());
      statement.setString(2, book.getAuthor());
      statement.setInt(3, book.getYear());
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      result = resultSet.getLong("result") > 0;
      resultSet.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    boolean result = false;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement
          .executeQuery("SELECT COUNT(*) as result FROM books");
      resultSet.next();
      result = resultSet.getLong("result") > 0;
      resultSet.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new DBConnectionException();
    }
    return result;
  }
}
