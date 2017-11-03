package com.bookshop.aspect;


import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RuntimeExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity handleExceptions(RuntimeException ex, WebRequest request) {
    Response response = new Response(Status.ERROR);
    return utils.create(ex, request, response);
  }
}
