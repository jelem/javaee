package com.greyslon.servlets.utils;

import java.io.Serializable;

public class HtmlHelper implements Serializable {

  public String divWrap(String content, String cssClass) {
    return "<div class=\"" + cssClass + "\">" + content + "</div>";
  }

  public String divWrap(String content) {
    return divWrap(content, "");
  }

}
