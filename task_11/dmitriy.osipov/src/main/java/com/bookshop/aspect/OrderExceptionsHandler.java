package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;
import com.bookshop.exception.OrderAlreadyExistsException;
import com.bookshop.exception.OrderException;
import com.bookshop.exception.OrderInvalidException;
import com.bookshop.exception.OrderNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop.controller"})
public class OrderExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(OrderException.class)
  protected ResponseEntity handleExceptions(OrderException ex, WebRequest request) {
    Response response = new Response(Status.ORDER_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(OrderAlreadyExistsException.class)
  protected ResponseEntity handleExistsExceptions(OrderAlreadyExistsException ex,
      WebRequest request) {
    Response response = new Response(Status.ORDER_EXISTS);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(OrderNotFoundException.class)
  protected ResponseEntity handleNotFoundExceptions(OrderNotFoundException ex, WebRequest request) {
    Response response = new Response(Status.ORDER_NOT_FOUND);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(OrderInvalidException.class)
  protected ResponseEntity handleInvalidExceptions(OrderInvalidException ex, WebRequest request) {
    Response response = new Response(Status.ORDER_INVALID);
    return utils.create(ex, request, response);
  }
}
