package com.hillel.servlets;

import com.hillel.servlets.utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newbook/item")
public class NeeBookItem extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpServletRequest request = req;
    request.setCharacterEncoding("UTF-8");

    HttpServletResponse response = resp;
    response.setContentType("text/html; charset=UTF-8");

    PrintWriter writer = response.getWriter();
    Utils.getInstance().displayAddedBook(request, writer);

    writer.close();

  }

}
