package com.task.bookshop.repository;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.model.Book;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepositoryFile implements BookRepository {

  private List<Book> bookList;
  private final File dataFile;
  private static final String filename = "bookList.txt";

  public BookRepositoryFile() {
    bookList = new ArrayList<>();
    dataFile = getResourceFile();
    loadData();
  }

  public BookRepositoryFile(File dataFile) {
    this.bookList = new ArrayList<>();
    this.dataFile = dataFile;
    loadData();
  }

  void setBookList(List<Book> bookList) {
    this.bookList = bookList;
  }

  @Override
  public void save(Book book) throws BookAlreadyExistsException {
    if (bookList.contains(book)) {
      throw new BookAlreadyExistsException("Book is already added");
    }
    bookList.add(book);
    saveData();
  }

  @Override
  public void update(Book book) throws BookNotExistsException {
    Optional<Book> oldBook = bookList.stream()
        .filter(x -> x.getId().equals(book.getId())).findFirst();
    int index = bookList.indexOf(oldBook
        .orElseThrow(() -> new BookNotExistsException("There is no book with such ID")));
    bookList.set(index, book);
    saveData();
  }

  @Override
  public boolean exists(Book book) {
    return bookList.contains(book);
  }

  @Override
  public List<Book> findAll() {
    return bookList;
  }

  @Override
  public Book getById(Long id) {
    return bookList.stream().filter(x -> x.getId().equals(id)).findFirst()
        .orElse(Book.getEmptyBook());
  }

  @Override
  public List<Book> findAllByAuthorLike(String author) {
    return bookList.stream().filter(x -> x.getAuthor().toLowerCase().contains(author.toLowerCase()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Book> findAllByYear(int year) {
    return bookList.stream().filter(x -> x.getYear() == year).collect(Collectors.toList());
  }

  @Override
  public List<Book> findAllByTitleLike(String title) {
    return bookList.stream().filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
        .collect(Collectors.toList());
  }

  private void loadData() {
    prepareResourceFile();
    ObjectMapper mapper = new ObjectMapper();
    try {
      JavaType javaType = mapper.getTypeFactory()
          .constructCollectionType(ArrayList.class, Book.class);
      List<Book> readed = mapper.readValue(dataFile, javaType);
      bookList.addAll(readed);
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  private void saveData() {
    ObjectMapper mapper = new ObjectMapper();
    try {

      mapper.writerWithDefaultPrettyPrinter();
      mapper.writeValue(dataFile, bookList);
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  @Override
  public boolean isEmpty() {
    return bookList.isEmpty();
  }

  private File getResourceFile() {
    File res;
    String datafilename;
    try {
      datafilename = getClass().getClassLoader()
          .getResource(filename).getFile();
    } catch (NullPointerException exc) {
      datafilename = filename;
    }
    res = new File(datafilename);
    return res;
  }

  private void prepareResourceFile() {
    if (!dataFile.exists()) {
      demoData();
      saveData();
    }
  }

  private void demoData() {
    bookList.add(new Book("Code Complete", "Steve McConnell", 2004));
    bookList.add(new Book("The Unified Software Development Process",
        "Grady Booch, Ivar Jacobson, James Rumbaugh", 1995));
    bookList.add(new Book("Java 8. The Complete Reference, 9th Edition",
        "Herbert Schildt", 2015));
    bookList.add(new Book(new String(("Java. Библиотека профессионала. Том 1. Основы")
        .getBytes(Charset.defaultCharset()), Charset.forName("UTF-8")),
        new String(("Хорстманн К., Корнелл Г.").getBytes(Charset.defaultCharset()),
            Charset.forName("UTF-8")), 2016));
    bookList.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2017));
    bookList.add(new Book("The Hobbit", "J.R.R. Tolkien", 2016));
    bookList.add(new Book("The Chronicles of Amber", "Roger Zelazny", 2016));
    bookList.add(new Book("The Steel Rat", "Harry Harrison", 2015));
    bookList.add(new Book("The Steel Rat", "Harry Harrison", 2017));
  }

  void saveForTest(Book book) throws BookAlreadyExistsException {
    if (bookList.contains(book)) {
      throw new BookAlreadyExistsException("Book is already added");
    }
    bookList.add(book);
  }

  void updateForTest(Book book) throws BookNotExistsException {
    Optional<Book> oldBook = bookList.stream()
        .filter(x -> x.getId().equals(book.getId())).findFirst();
    int index = bookList.indexOf(oldBook
        .orElseThrow(() -> new BookNotExistsException("There is no book with such ID")));
    bookList.set(index, book);
  }
}
