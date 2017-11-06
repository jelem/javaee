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
        .hasAuthority("ADMIN")
        .antMatchers("/orders/buy*", "/orders/*")
        .hasAuthority("CUSTOMER")
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
            "select u.login as username, r.name as role from users u "
                + "join user_role ur "
                + "join roles r "
                + "on r.id=ur.role_id and u.id=ur.user_id "
                + "where u.login=?");
  }
}
