package com.hillel.servlets;

import com.hillel.data.DataBase;
import com.hillel.servlets.utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class PrintAllBooksServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();
    Utils.getInstance().display(writer, DataBase.getInstance().getBookList());
    writer.close();

  }
}
