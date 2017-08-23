package com.hillel.servlets;

import com.hillel.data.DataBase;
import com.hillel.objects.Book;
import com.hillel.servlets.utils.Utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/search/author")
public class ShowAuthorServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();
    req.setCharacterEncoding("UTF-8");
    String authorRequest = StringEscapeUtils.escapeHtml4(req.getParameter("authorname").trim());
    TreeMap<String, Book> authorBooks = DataBase.getInstance().findBooksOwnerAuthor(authorRequest);

    if (authorBooks.size() == 1 && authorBooks.containsKey("Empty_MAP")) {
      writer.println("К сожалению, по Вашему запросу не найдено ни одной книги!!!<br>");
      writer.println("Ваш запрос по автору: <strong>" + authorRequest + "</strong");
    } else {
      Utils.getInstance().display(writer, authorBooks);
    }

    writer.close();

  }
}
