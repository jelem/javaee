package com.task.bookshop.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class LinkGeneratorTest {

  @Test
  public void getBookLinkShouldReturnSpecifiedString() {
    String expected = String.format("<a href='/bookshop/servlet/book?id=%s'>%s</a>", "abc", "def");
    assertThat(LinkGenerator.getBookLink("abc", "def"), is(expected));
  }

  @Test
  public void getAuthorsLinkShouldReturnSpecifiedString() {
    String expected = String.format("<a href='/bookshop/servlet/find/author?author=%s'>%s</a>",
        "abc", "abc");
    assertThat(LinkGenerator.getAuthorsLink("abc"), is(expected));
  }

  @Test
  public void getYearLinkShouldReturnSpecifiedString() {
    String expected = String.format("<a href='/bookshop/servlet/find/year?year=%d'>%d</a>",
        2017, 2017);
    assertThat(LinkGenerator.getYearLink(2017), is(expected));
  }
}
