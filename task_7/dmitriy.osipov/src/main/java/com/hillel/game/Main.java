package com.hillel.game;

import com.hillel.game.config.AppConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    TicTacToe ticTacToe = applicationContext.getBean(TicTacToe.class);
    ticTacToe.startGame();

  }
}
