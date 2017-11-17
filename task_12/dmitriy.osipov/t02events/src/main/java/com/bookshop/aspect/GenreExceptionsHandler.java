package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;
import com.bookshop.exception.GenreAlreadyExistsException;
import com.bookshop.exception.GenreException;
import com.bookshop.exception.GenreInvalidException;
import com.bookshop.exception.GenreNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop.controller"})
public class GenreExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(GenreException.class)
  protected ResponseEntity handleExceptions(GenreException ex, WebRequest request) {
    Response response = new Response(Status.GENRE_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(GenreAlreadyExistsException.class)
  protected ResponseEntity handleExistsExceptions(GenreAlreadyExistsException ex,
      WebRequest request) {
    Response response = new Response(Status.GENRE_EXISTS);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(GenreNotFoundException.class)
  protected ResponseEntity handleNotFoundExceptions(GenreNotFoundException ex, WebRequest request) {
    Response response = new Response(Status.GENRE_NOT_FOUND);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(GenreInvalidException.class)
  protected ResponseEntity handleInvalidExceptions(GenreInvalidException ex, WebRequest request) {
    Response response = new Response(Status.GENRE_INVALID);
    return utils.create(ex, request, response);
  }

}
