package com.task.bookshop.utils;

public class HtmlPagePreparer {

  private String getBookLink(String bookId, String linkTitle) {
    return String.format("<a href='/bookshop/servlet/book?id=%s'>%s</a>", bookId, linkTitle);
  }

  private String getAuthorsLink(String author) {
    return String
        .format("<a href='/bookshop/servlet/find/author?author=%s'>%s</a>", author, author);
  }

  private String getYearLink(int year) {
    return String.format("<a href='/bookshop/servlet/find/year?year=%d'>%d</a>", year, year);
  }

}
