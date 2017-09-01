package com.task.bookshop.utils;

import com.task.bookshop.model.Book;

import java.util.List;

public class HtmlPagePreparer {

  public String prepareForTable(String title, List<Book> books) {
    return getHead(title)
        .concat(getTable(books))
        .concat(getFooter());
  }

  public String prepareForOneBook(Book book) {
    String startCell = "\n\t\t\t<td>";
    String endCell = "\n\t\t\t</td>";
    String startRow = "\n\t\t<tr>";
    String endRow = "\n\t\t</tr>";
    return getHead(book.getTitle())
        .concat("\n\t<p align=center>\n\t<table border=1>")
        .concat(startRow)
        .concat(startCell).concat("Title:").concat(endCell)
        .concat(startCell).concat(book.getTitle()).concat(endCell)
        .concat(endRow).concat(startRow)
        .concat(startCell).concat("Author:").concat(endCell)
        .concat(startCell).concat(getAuthorsLink(book.getAuthor())).concat(endCell)
        .concat(endRow).concat(startRow)
        .concat(startCell).concat("Year:").concat(endCell)
        .concat(startCell).concat(getYearLink(book.getYear())).concat(endCell)
        .concat(endRow)
        .concat("\n\t</table>\n\t</p>")
        .concat(getFooter());
  }

  private String getBookLink(String bookId, String linkTitle) {
    return String.format("<a href='/bookshop/servlet/book?id=%s'>%s</a>", bookId, linkTitle);
  }

  private String getAuthorsLink(String author) {
    return String.format("<a href='/bookshop/servlet/find/author?author=%s'>%s</a>", author, author);
  }

  private String getYearLink(int year) {
    return String.format("<a href='/bookshop/servlet/find/year?year=%d'>%d</a>", year, year);
  }

  private String getHead(String title) {
    return "<html>\n<head>\n\t<title>"
        .concat(title)
        .concat("\n\t</title>")
        .concat("\n\t<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"utf-8\">")
        .concat("\n</head>\n<body>\n\t<h1>")
        .concat(title)
        .concat("\n\t</h1>");
  }

  private String getTable(List<Book> rows) {
    StringBuilder builder = new StringBuilder();
    String headerStart = "\n\t\t\t<th>";
    String headerEnd = "\n\t\t\t</th>";
    String cellStart = "\n\t\t\t<td word-wrap=\"break-word\">";
    String cellEnd = "\n\t\t\t</td>";
    builder.append("\n\t<p align = \"center\">\n\t<table border=1>")
        .append("\n\t\t<tr>")
        .append(headerStart).append("Author").append(headerEnd)
        .append(headerStart).append("Title").append(headerEnd)
        .append(headerStart).append("Year").append(headerEnd)
        .append("\n\t\t</tr>");

    for (Book book : rows) {
      builder.append("\n\t\t<tr>")
          .append(cellStart).append(getAuthorsLink(book.getAuthor())).append(cellEnd)
          .append(cellStart).append(getBookLink(book.getId(), book.getTitle())).append(cellEnd)
          .append(cellStart).append(getYearLink(book.getYear())).append(cellEnd)
          .append("\n\t\t</tr>");
    }

    builder.append("\n\t</table>\n\t</p>");
    return builder.toString();
  }

  private String getFooter() {
    return "\n\t</br>"
        .concat("\n\t<p align = center><a href='/bookshop'>To main page</a></p>")
        .concat("\n</body>\n</Html>");
  }

  private String getStyles() {
    return "table {\n"
        + "    table-layout: fixed;\n"
        + "    width:100%\n"
        + "    borders:1\n"
        + "}\n"
        + ".cell {\n"
        + "    word-wrap:break-word;\n"
        + "}";
  }
}
