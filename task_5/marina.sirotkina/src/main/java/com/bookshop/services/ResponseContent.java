package com.bookshop.services;

import com.bookshop.domain.Book;
import com.bookshop.utils.BlobConverter;

import java.io.Serializable;
import java.util.List;

public class ResponseContent implements Serializable {

  private BlobConverter blobConverter;

  public ResponseContent() {
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
    sb.append("         <th>Description</th>\n");
    sb.append("         <th>Illustration</th>\n");
    sb.append("         <th>Price</th>\n");
    sb.append("      </tr>\n");
    return sb.toString();
  }

  private String innerContent(Book book) {
    blobConverter = new BlobConverter();
    StringBuilder sb = new StringBuilder();
    sb.append("      <tr>\n");
    sb.append("         <td>").append(book.getTitle()).append("</td>\n");
    sb.append("         <td>").append(book.getAuthor()).append("</td>\n");
    sb.append("         <td>").append(book.getDescription()).append("</td>\n");
    sb.append("         <iframe frameborder=\"0\" width=\"300px\" height=\"300px\">")
        .append(blobConverter.readImageFromDb(book.getIllustration()))
        .append("</iframe>\n");
    sb.append("         <td>").append(book.getPrice()).append("</td>\n");
    sb.append("      </tr>");
    return sb.toString();
  }
}
