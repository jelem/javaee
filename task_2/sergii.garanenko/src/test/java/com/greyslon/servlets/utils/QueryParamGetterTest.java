package com.greyslon.servlets.utils;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.Optional;

public class QueryParamGetterTest {

  private QueryParamGetter paramGetter = new QueryParamGetter();

  @Test
  public void getParam() throws Exception {
    String queryString = "param=22&param2=bar";
    Optional<String> param = paramGetter.getParam(queryString, "param");
    assertThat(param.get()).isEqualTo("22");
  }
  @Test
  public void getParam2() throws Exception {
    String queryString = "param2=bar";
    Optional<String> param = paramGetter.getParam(queryString, "param");
    assertThat(param.isPresent()).isFalse();
  }
  @Test
  public void getParam3() throws Exception {
    String queryString = "param2=bar&param=22";
    Optional<String> param = paramGetter.getParam(queryString, "param");
    assertThat(param.get()).isEqualTo("22");
  }

}