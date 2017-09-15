package com.bookshop.servlets;

import com.google.common.io.CharStreams;

import com.bookshop.dao.BookDao;
import com.bookshop.domain.Book;
import com.bookshop.services.ResponseContent;
import com.bookshop.utils.BlobConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add")
@MultipartConfig
public class AddBookServlet extends HttpServlet {

  private String title;
  private String author;
  private String description;
  private Blob illustration;
  private BigDecimal price;
  private ResponseContent responseContent;
  private BookDao bookDao;
  private BlobConverter blobConverter;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    bookDao = new BookDao();
    blobConverter = new BlobConverter();

    List<FileItem> multiparts = null;
    try {
      multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
    } catch (FileUploadException ex) {
      ex.printStackTrace();
    }
    if (multiparts != null) {
      illustration = blobConverter.readImage(multiparts.get(3).getInputStream());
      title = parseMultiparts(multiparts.get(0).getInputStream());
      author = parseMultiparts(multiparts.get(1).getInputStream());
      description = parseMultiparts(multiparts.get(2).getInputStream());
      price = BigDecimal.valueOf(Double.valueOf(parseMultiparts(multiparts.get(4).getInputStream())));
    }

    PrintWriter out = resp.getWriter();
    bookDao.save(
        new Book(title, author, description, illustration, price));
    responseContent = new ResponseContent();
    out.println(responseContent.getHtmlContent(bookDao.getAll()));
  }


  private String parseMultiparts(InputStream stream) {
    String result = "";
    try {
      result = CharStreams.toString(new InputStreamReader(stream,
          Charset.defaultCharset()));
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        stream.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }
}
