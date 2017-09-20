package com.hillel.game;

import com.hillel.game.config.ApplicationConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    TicTacToe ticTacToe = context.getBean(TicTacToe.class);
    ticTacToe.startGame();
  }
}
