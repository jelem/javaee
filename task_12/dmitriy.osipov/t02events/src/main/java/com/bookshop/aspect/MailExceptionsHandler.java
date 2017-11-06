package com.bookshop.aspect;

import com.bookshop.aspect.utils.ExceptionAspectsUtils;
import com.bookshop.domain.Response;
import com.bookshop.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.bookshop"})
public class MailExceptionsHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionAspectsUtils utils;

  @ExceptionHandler(MailException.class)
  protected ResponseEntity handleExceptions(MailException ex, WebRequest request) {
    Response response = new Response(Status.MAIL_ERROR);
    return utils.create(ex, request, response);
  }

  @ExceptionHandler(MailSendException.class)
  protected ResponseEntity handleExceptions(MailSendException ex, WebRequest request) {
    Response response = new Response(Status.MAIL_ERROR);
    return utils.create(ex, request, response);
  }
}
