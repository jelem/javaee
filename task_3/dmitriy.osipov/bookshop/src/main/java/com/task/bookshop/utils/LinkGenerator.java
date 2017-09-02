package com.task.bookshop.utils;

public class LinkGenerator {
  public static String getBookLink(String bookId, String linkTitle) {
    return String.format("<a href='/bookshop/servlet/book?id=%s'>%s</a>", bookId, linkTitle);
  }

  public static String getAuthorsLink(String author) {
    return String.format("<a href='/bookshop/servlet/find/author?author=%s'>%s</a>", author, author);
  }

  public static String getYearLink(int year) {
    return String.format("<a href='/bookshop/servlet/find/year?year=%d'>%d</a>", year, year);
  }
}
