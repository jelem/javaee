package com.bookshop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.bookshop.domain.Book;
import com.bookshop.utils.BlobConverter;
import com.bookshop.utils.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookDaoTest {

  private static BookDao bookDao;
  private static ConnectionUtil connectionUtil;
  private static BlobConverter blobConverter;

  @BeforeClass
  public static void before() {
    connectionUtil = new ConnectionUtil();
    bookDao = new BookDao();
    blobConverter = new BlobConverter();
  }

  @Before
  public void setUp() throws Exception {
    String query = "CREATE TABLE books ("
        + "  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
        + "  title VARCHAR(255) NOT NULL ,"
        + "  author VARCHAR(255) NOT NULL ,"
        + "  description TEXT ,"
        + "  illustration BLOB ,"
        + "  price NUMERIC(15,2) NOT NULL "
        + ") ENGINE = InnoDB DEFAULT CHARSET = utf8;";

    try(Connection connection = connectionUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)){
      ps.executeUpdate();
    }

    bookDao.save(new Book("Портрет Дориана Грея", "Оскар Уайльд",
        "2016", blobConverter.readImage("martian.jpg"), BigDecimal.valueOf(45.9)));

    bookDao.save(new Book("The Martian", "Andy Weir", "2011",
        blobConverter.readImage("martian.jpg"), BigDecimal.valueOf(76.3)));

  }

  @After
  public void tearDown() throws Exception {
    try(Connection connection = connectionUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("DROP DATABASE `mockbookshopdb`")){
      ps.executeUpdate();
    }
  }

  @Test
  public void shouldSaveNewBookIntoDb() throws Exception {
    Book book = bookDao.getById(1);
    assertNotNull(book);
    assertEquals("Портрет Дориана Грея", book.getTitle());
  }

  @Test
  public void shouldGetAllBooksFromDb() throws Exception {
    List<Book> bookList = bookDao.getAll();
    assertNotNull(bookList);
    assertEquals(Long.valueOf(1), bookList.get(0).getId());
  }

  @Test
  public void shouldUpdateBookInDb() throws Exception {
    bookDao.update(new Book(2L, "The Martian", "Andy Weir",
        "2016", blobConverter.readImage("martian.jpg"), BigDecimal.valueOf(85.40)));
    Book book = bookDao.getById(2);
    assertEquals("2016", book.getDescription());
    assertEquals(BigDecimal.valueOf(85.40).setScale(2, BigDecimal.ROUND_UNNECESSARY),
        book.getPrice());
  }

  @Test
  public void shouldDeleteBookFromDb() throws Exception {
    bookDao.deleteById(1);
    Book book = bookDao.getById(1);
    assertNull(book);

    List<Book> bookList = bookDao.getAll();
    assertNotNull(bookList);
    assertEquals(Long.valueOf(2), bookList.get(0).getId());
  }
}