package com.hillel.bookshop.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.hillel.bookshop.model.books.Book;
import com.hillel.bookshop.model.books.BookDescription;
import com.hillel.bookshop.model.category.Category;
import com.hillel.bookshop.model.category.CategoryDescription;
import com.hillel.bookshop.model.illustrations.Illustration;
import com.hillel.bookshop.service.BookService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookshopControllerWebIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private BookService bookService;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void saveNewBook() throws Exception {
    HttpEntity<Book> request = new HttpEntity<>(new Book());
    ResponseEntity<String> response = restTemplate.exchange("/newBook", HttpMethod.POST,
        request, String.class);

    assertThat(response.getStatusCode(), is(HttpStatus.OK));

    String body = response.getBody();
    assertEquals(body, "saving is success");
  }

  @Test
  public void findBooksByAuthor() throws Exception {
    Category category = new Category();
    CategoryDescription categoryDescription = new CategoryDescription();
    categoryDescription.setCategoryTitle("category");
    Set<CategoryDescription> categoryDescriptions = new HashSet<>();
    categoryDescriptions.add(categoryDescription);
    category.setCategoryDescriptions(categoryDescriptions);

    BookDescription bookDescription = new BookDescription();
    bookDescription.setBookTitle("title");
    bookDescription.setBookAuthor("author");
    bookDescription.setBookPublishingYear("1234");

    Set<Illustration> illustrations = new HashSet<>();
    illustrations.add(new Illustration());

    bookService.saveNewBook(new Book(BigDecimal.valueOf(10.2), category, bookDescription,
        illustrations, "12345"));

    ResponseEntity<Book[]> response = restTemplate.getForEntity("/books/author", Book[].class);

    assertThat(response.getStatusCode(), is(HttpStatus.OK));

    Book[] body = response.getBody();
    Book book = body[0];
    assertNotNull(book);
    assertThat(book.getId(), is(2L));
    assertThat(book.getPrice(),
        is(BigDecimal.valueOf(10.2).setScale(2, BigDecimal.ROUND_UNNECESSARY)));
    assertThat(book.getBookDescription().getBookAuthor(), is("author"));
  }

  @Test
  public void updateBook() throws Exception {
    HttpEntity<Book> request = new HttpEntity<>(new Book());
    ResponseEntity<String> response = restTemplate.exchange("/book/1", HttpMethod.PUT,
        request, String.class);

    assertThat(response.getStatusCode(), is(HttpStatus.OK));

    String body = response.getBody();
    assertEquals(body, "updating is success");
  }

  @Test
  public void deleteBook() throws Exception {
    HttpEntity<Book> request = new HttpEntity<>(new Book());
    ResponseEntity<String> response = restTemplate.exchange("/book/1", HttpMethod.DELETE,
        request, String.class);

    assertThat(response.getStatusCode(), is(HttpStatus.OK));

    String body = response.getBody();
    assertEquals(body, "deleting is success");
  }

}