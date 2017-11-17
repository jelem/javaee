package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;
import com.bookshop.exception.BookAlreadyExistsException;
import com.bookshop.exception.BookException;
import com.bookshop.exception.BookInvalidException;
import com.bookshop.exception.BookNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop.controller"})
public class BookExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(BookException.class)
  protected ResponseEntity handleExceptions(BookException ex, WebRequest request) {
    Response response = new Response(Status.BOOK_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  protected ResponseEntity handleExistsExceptions(BookAlreadyExistsException ex,
      WebRequest request) {
    Response response = new Response(Status.BOOK_EXISTS);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(BookNotFoundException.class)
  protected ResponseEntity handleNotFoundExceptions(BookNotFoundException ex, WebRequest request) {
    Response response = new Response(Status.BOOK_NOT_FOUND);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(BookInvalidException.class)
  protected ResponseEntity handleInvalidExceptions(BookInvalidException ex, WebRequest request) {
    Response response = new Response(Status.BOOK_INVALID);
    return utils.create(ex, request, response);
  }
}
