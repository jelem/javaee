package com.task.bookshop.utils;

import java.nio.charset.Charset;

public class StringConverter {

  /**
   * Creates a new String from existing with specified encoding
   * @param inputString input string in inputCharset
   * @param inputCharset input string charset name
   * @param outputCharset result string charset name
   * @return string in outputCharset
   */
  public static String convertString(String inputString,
      String inputCharset, String outputCharset) {
    return new String(inputString
        .getBytes(Charset.forName(inputCharset)), Charset.forName(outputCharset));
  }

  /**
   * Creates a new String in "UTF-8" charset from input string in "ISO-8859-1" charset
   * @param inputString string in "ISO-8859-1" charset
   * @return string in "UTF-8"
   */
  public static String convertString(String inputString) {
    return new String(inputString
        .getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
  }
}
