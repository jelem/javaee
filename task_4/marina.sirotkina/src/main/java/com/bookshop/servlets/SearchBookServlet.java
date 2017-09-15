package com.bookshop.servlets;

import com.bookshop.entity.Book;
import com.bookshop.services.BookDB;
import com.bookshop.services.ResponseContent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search/result")
public class SearchBookServlet extends HttpServlet {

  private String author;
  private ResponseContent responseContent;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    responseContent = new ResponseContent();
    author = req.getParameter("author");
    PrintWriter out = resp.getWriter();
    out.println(responseContent.getHtmlContent(getBookByAuthor()));
  }

  private List<Book> getBookByAuthor() {
    return BookDB.INSTANCE.searchBook(author);
  }
}
