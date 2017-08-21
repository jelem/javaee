package com.hillel.servlets.utils;

import com.hillel.data.DataBase;
import com.hillel.objects.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

public class Utils {

  private Utils() {
  }

  private static Utils utils;

  public static Utils getInstance() {
    if (utils == null) {
      utils = new Utils();
    }

    return utils;
  }

  public void display(PrintWriter writer, TreeMap<String, Book> mapBooks) throws IOException {

    writer.println("<html>");
    writer.println("<head>\n" +
        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
        "  <title>Таблица</title>\n" +
        "  <style type=\"text/css\">\n" +
        "   TABLE {\n" +
        "    width: 600px; /* Ширина таблицы */\n" +
        "    border-collapse: collapse; /* Убираем двойные линии между ячейками */\n" +
        "   }\n" +
        "   TD, TH {\n" +
        "    padding: 5px; /* Поля вокруг содержимого таблицы */\n" +
        "    border: 1px solid black; /* Параметры рамки */\n" +
        "   }\n" +
        "   TH {\n" +
        "    background: #b0e0e6; /* Цвет фона */\n" +
        "   }\n" +
        "  </style>\n" +
        " </head>");
    writer.println("<form><table><tr>");
    writer.println("<td><center><b>№</b></center></td>");
    writer.println("<td><center><b>Имя книги</b></center></td>");
    writer.println("<td><center><b>Автор книги</b></center></td>");
    writer.println("<td><center><b>Год публикации</b></center></td>");
    writer.println("</tr>");

    int count = 1;

    for (Map.Entry<String, Book> bookEntry : mapBooks.entrySet()) {
      writer.println("<tr>");
      writer.println("<td><center>" + count + "</center></td>");
      writer.println(
          "<td><center>" + bookEntry.getValue().getBookName()
              + "</center></td>");
      writer.println(
          "<td><center>" + bookEntry.getValue().getBookAuthor()
              + "</center></td>");
      writer.println(
          "<td><center>" + bookEntry.getValue().getBookPublishYear()
              + "</center></td>");
      writer.println("</tr>");
      count++;
    }

    writer.println("</table></form></html>");

  }

  public void displayAddedBook(HttpServletRequest request, PrintWriter writerParametr)
      throws IOException {

    boolean addedNewFlag = false;
    String newBookName = request.getParameter("newbookname"),
        newBookAuthor = request.getParameter("newbookauthor"),
        newBookYear = request.getParameter("newbookyear");

    TreeMap<String, Book> treeMapOfAddedValue = new TreeMap();
    treeMapOfAddedValue.put(newBookName, new Book(newBookName, newBookAuthor, newBookYear));

    addedNewFlag = DataBase.getInstance().addToBookList(treeMapOfAddedValue);

    if (addedNewFlag) {
      writerParametr.println("<h2>Поздравляем! Вы только что добавили новую книгу:</h2><br>");
      display(writerParametr, treeMapOfAddedValue);
    } else {
      writerParametr.println("Похоже что-то пошло не так ....!");
    }

  }
}