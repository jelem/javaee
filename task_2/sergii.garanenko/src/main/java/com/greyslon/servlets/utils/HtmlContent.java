package com.greyslon.servlets.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HtmlContent implements Serializable {

  public String renderContent(String content) throws IOException {

    String template = getTemplate("template.txt");

    template = template.substring(3);
    return template.replace("$RenderBody", content);
  }

  public String renderNavigation() throws IOException {
    return getTemplate("navigation.txt");
  }

  private String getTemplate(String templateName) throws IOException {
    File file = new File(getClass()
        .getClassLoader()
        .getResource(templateName)
        .getFile());
    String template = null;
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      template = reader
          .lines()
          .collect(Collectors.joining(System.getProperty("line.separator")));
    }
    return template;
  }
}
