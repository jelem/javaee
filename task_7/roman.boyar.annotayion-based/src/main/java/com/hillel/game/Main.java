package com.hillel.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
    TicTacToe ticTacToe = appContext.getBean("tictactoe", TicTacToe.class);
    ticTacToe.startGame();

  }
}
