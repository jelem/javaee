package com.bookshop.services;

import com.bookshop.entity.Book;

import java.io.Serializable;
import java.util.List;

public class ResponseContent implements Serializable {

  private Book book;

  public ResponseContent() {
  }

  public ResponseContent(Book book) {
    this.book = book;
  }

  public String getHtmlContent() {
    BookDB.INSTANCE.updateBookList(book);
    StringBuilder sb = new StringBuilder();
    sb.append(headerContent());
    for (Book book : BookDB.INSTANCE.getBookList()) {
      sb.append(innerContent(book));
    }
    return sb.toString();
  }

  public String getHtmlContent(List<Book> bookList) {
    StringBuilder sb = new StringBuilder();
    sb.append(headerContent());
    if (!bookList.isEmpty()) {
      for (Book book : bookList) {
        sb.append(innerContent(book));
      }
    } else {
      sb.append("<td>There is no such book in the store</td>");
    }
    return sb.toString();
  }

  private String headerContent() {
    StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>\n");
    sb.append("<html lang=\"en\">\n");
    sb.append("<head>\n");
    sb.append("<table>\n");
    sb.append("      <tr>\n");
    sb.append("         <th>Title</th>\n");
    sb.append("         <th>Author</th>\n");
    sb.append("         <th>Year</th>\n");
    sb.append("      </tr>\n");
    return sb.toString();
  }

  private String innerContent(Book book) {
    StringBuilder sb = new StringBuilder();
    sb.append("      <tr>\n");
    sb.append("         <td>").append(book.getTitle()).append("</td>\n");
    sb.append("         <td>").append(book.getAuthor()).append("</td>\n");
    sb.append("         <td>").append(book.getYear()).append("</td>\n");
    sb.append("      </tr>");
    return sb.toString();
  }
}
