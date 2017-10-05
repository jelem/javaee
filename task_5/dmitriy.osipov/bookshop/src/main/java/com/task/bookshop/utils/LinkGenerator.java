package com.task.bookshop.utils;

import com.task.bookshop.repository.BeansContainer;

import java.nio.charset.Charset;
import java.util.Base64;

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

  public static String getBookImageAsTag(Object image) {
    if (image != null) {
      byte[] bytes = Base64.getEncoder().encode((byte[])image);
      String encodedBytes = new String(bytes, Charset.forName("UTF-8"));
      return "<img src=\"data:image/jpeg;base64," + encodedBytes + "\" class=\"bookImg\">";
    } else {
      return null;
    }
  }
}
