package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;
import com.bookshop.exception.AuthorAlreadyExistsException;
import com.bookshop.exception.AuthorException;
import com.bookshop.exception.AuthorInvalidException;
import com.bookshop.exception.AuthorNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop.controller"})
public class AuthorExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(AuthorException.class)
  protected ResponseEntity handleExceptions(AuthorException ex, WebRequest request) {
    Response response = new Response(Status.AUTHOR_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(AuthorAlreadyExistsException.class)
  protected ResponseEntity handleExistsExceptions(AuthorAlreadyExistsException ex,
      WebRequest request) {
    Response response = new Response(Status.AUTHOR_EXISTS);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(AuthorNotFoundException.class)
  protected ResponseEntity handleNotFoundExceptions(AuthorNotFoundException ex,
      WebRequest request) {
    Response response = new Response(Status.AUTHOR_NOT_FOUND);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(AuthorInvalidException.class)
  protected ResponseEntity handleInvalidExceptions(AuthorInvalidException ex, WebRequest request) {
    Response response = new Response(Status.AUTHOR_INVALID);
    return utils.create(ex, request, response);
  }
}
