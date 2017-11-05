package com.bookshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/*/add*", "/*/update*",
            "/**/delete*", "/orders/bydone", "/orders/all",
            "/orders/*", "/orders/byuser")
        .hasAuthority("1")
        .antMatchers("/orders/buy*", "/orders/*")
        .hasAuthority("0")
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select u.login as username, u.password, true as enabled from users u "
                + "where u.login=?")
        .authoritiesByUsernameQuery(
            "select u.login as username, u.role as role from users u "
                + "where u.login=?");
  }
}
