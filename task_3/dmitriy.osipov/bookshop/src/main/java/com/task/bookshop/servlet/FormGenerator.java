package com.task.bookshop.servlet;

import com.task.bookshop.model.Book;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/formgen")
public class FormGenerator extends HttpServlet {

  public FormGenerator() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String condition = req.getParameter("condition");
    if (condition == null) {
      condition = "title";
    }

    String title = req.getParameter("title");
    if (title == null) {
      title = "названию";
    }

    req.setAttribute("requestForm",
        new String(generateFindForm(condition, "get").getBytes(Charset.defaultCharset()),
            Charset.forName("UTF-8")));
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/requestform.jsp");
    dispatcher.forward(req, resp);
  }

  private String generateFindForm(String condition, String method) {
    String inputType = "text";
    try {
      Field field = Book.class.getDeclaredField(condition);
      if (field.getType().isAssignableFrom(Number.class)
          || field.getType().getSimpleName().equals("int")
          || field.getType().getSimpleName().equals("long")) {
        inputType = "number";
      }
    } catch (NoSuchFieldException exc) {
      exc.printStackTrace();
    }
    return "<form action=\"/bookshop/servlet/find/" + condition + "\" method=\"" + method
        + "\" class=\"requestForm\">\n\t<input type=\"" + inputType + "\" name=\"" + condition
        + "\" class=\"formEntry\">"
        + "\n\t<input type=\"submit\" value=\"Поиск\" class=\"formEntry\">\n</form>";
  }
}
