package com.greyslon.servlets.utils;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParamGetter implements Serializable {

  public Optional<String> getParam(String queryString, String param) {
    Objects.requireNonNull(queryString, "QueryString is required");

    String paramPattern = "(?<=" + param + "=)[^&]+";
    Pattern pattern = Pattern.compile(paramPattern);
    String value = null;
    Matcher matcher = pattern.matcher(queryString);
    if (matcher.find()) {
      value = matcher.group();
    }
    return value == null
        ? Optional.empty()
        : Optional.of(value);
  }
}
