package com.greyslon.servlets.utils;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Encoder implements Serializable {

  public String encodeToUTF8(String text, Charset charsetIn) {
    return new String(text.getBytes(charsetIn), StandardCharsets.UTF_8);
  }
}
