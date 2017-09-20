package com.hillel.game;

import com.hillel.game.config.AppConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {

    ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    TicTacToe ticTacToe = appContext.getBean(TicTacToe.class);
    ticTacToe.startGame();

  }
}
