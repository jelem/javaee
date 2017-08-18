package com.bookshop.servlets;

import com.bookshop.services.ResponseContent;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class PrintBooksServlet extends HttpServlet {

  private ResponseContent responseContent;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    responseContent = new ResponseContent();
    PrintWriter out = resp.getWriter();
    out.println(responseContent.getHTMLContent());
  }

}
