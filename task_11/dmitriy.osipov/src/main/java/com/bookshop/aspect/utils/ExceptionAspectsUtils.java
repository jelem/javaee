package com.bookshop.aspect.utils;

import com.bookshop.domain.Response;
import com.bookshop.domain.ResponseKeys;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class ExceptionAspectsUtils extends ResponseEntityExceptionHandler {

  public ResponseEntity create(Exception exception, WebRequest req, Response response) {
    response.put(ResponseKeys.EXCEPTION_TYPE, exception.getClass());
//    response.put(ResponseKeys.EXCEPTION, exception);
    response.put(ResponseKeys.EXCEPTION_MESSAGE, exception.getMessage());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(exception, response, headers, HttpStatus.OK, req);
  }
}
