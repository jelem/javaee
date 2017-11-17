package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;
import com.bookshop.exception.UserAlreadyExistsException;
import com.bookshop.exception.UserException;
import com.bookshop.exception.UserInvalidException;
import com.bookshop.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop.controller"})
public class UserExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(UserException.class)
  protected ResponseEntity handleExceptions(UserException ex, WebRequest request) {
    Response response = new Response(Status.USER_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  protected ResponseEntity handleExistsExceptions(UserAlreadyExistsException ex,
      WebRequest request) {
    Response response = new Response(Status.USER_EXISTS);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity handleNotFoundExceptions(UserNotFoundException ex, WebRequest request) {
    Response response = new Response(Status.USER_NOT_FOUND);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(UserInvalidException.class)
  protected ResponseEntity handleInvalidExceptions(UserInvalidException ex, WebRequest request) {
    Response response = new Response(Status.USER_INVALID);
    return utils.create(ex, request, response);
  }

}
