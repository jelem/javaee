package com.bookshop.domain;

import java.util.HashMap;

public class Response extends HashMap<ResponseKeys, Object> {

  public Response() {
    this(Status.OK);
  }

  public Response(Status status, ResponseKeys key, Object value) {
    this(status);
    this.put(key, value);
  }

  public Response(ResponseKeys key, Object value) {
    this();
    this.put(key, value);
  }

  public Response(Status status) {
    super();
    this.put(ResponseKeys.STATUS, status);
  }

}
