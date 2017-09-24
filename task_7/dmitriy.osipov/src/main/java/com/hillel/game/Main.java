package com.hillel.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

    TicTacToe ticTacToe = applicationContext.getBean("tic-tac-game", TicTacToe.class);
    ticTacToe.startGame();

  }
}
