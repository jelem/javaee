package com.task.bookshop.servlet;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.ImageUploadException;
import com.task.bookshop.model.Book;
import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;
import com.task.bookshop.utils.StringConverter;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

@WebServlet("/servlet/newbook")
public class NewBook extends HttpServlet {

  private static final long serialVersionUID = 170820030004L;

  private transient BookRepository bookRepository;

  public NewBook() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      Book newBook = upload(req);
      bookRepository.save(newBook);
    } catch (FileUploadException uploadExc) {
      uploadExc.printStackTrace();
      throw new ImageUploadException(uploadExc.getMessage());
    } catch (BookAlreadyExistsException exc) {
      exc.printStackTrace();
    }
    resp.sendRedirect("/bookshop/servlet/allbooks");
  }

  private Book upload(HttpServletRequest request) throws FileUploadException {
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List<DiskFileItem> fileItems = upload.parseRequest(request);
    Book book = Book.getEmptyBook();
    book.setTitle(StringConverter.convertString(fileItems.stream()
        .filter(x -> x.getFieldName().equals("title"))
        .findFirst().orElse(null).getString()));
    book.setAuthor(StringConverter.convertString(fileItems.stream()
        .filter(x -> x.getFieldName().equals("author"))
        .findFirst().orElse(null).getString()));
    book.setYear(Integer.parseInt(fileItems.stream().filter(x -> x.getFieldName().equals("year"))
        .findFirst().orElse(null).getString()));
    byte[] img = fileItems.stream().filter(x -> x.getFieldName().equals("upfile"))
        .findFirst().orElse(null).get();
    try {
      book.setImage(new SerialBlob(img));
    } catch (SQLException sqlExc) {
      throw new ImageUploadException(sqlExc.getMessage());
    }
    return book;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("newBookForm", createForm());
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/newbook.jsp");
    dispatcher.forward(req, resp);
  }

  private String createForm() {
    return "<form action=\"/bookshop/servlet/newbook\" method=\"post\" class=\"requestForm\">\n"
        + "\t<h2>Author:</h2>"
        + "<input type=\"text\" name=\"author\" class=\"formEntry\">\n"
        + "\t<h2>Title:</h2>"
        + "<input type=\"text\" name=\"title\" class=\"formEntry\">\n"
        + "\t<h2>Year:</h2>"
        + "<input type=\"number\" name=\"year\" value=\"2017\" class=\"formEntry\">\n"
        + "\t<input type=\"submit\" value=\"Add\" class=\"formEntry\">\n"
        + "</form>";
  }
}
