package com.hillel.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// В этом классе необходимо заменить встроенные объекты
// на Dependency Injection с помощью Spring
public class TicTacToe {

  ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");

  private Board board = new Board();

  // В этой программе эти классы ничего не делают.
  // Вам нужно добавить 2 реализации игрока:
  //  - компьютер
  //  - человек
  private Player player1 = appContext.getBean("human", HumanPlayer.class);
  private Player player2 = appContext.getBean("computer", ComputerPlayer.class);

  // Игрок, который в данный момент делает ход.
  // Тип данных должен быть Player и хранить
  // одного из игроков
  private Player currentPlayer = player1;

  public void startGame() {
    player1.setName("John");
    player2.setName("Robert");

    player1.setSymbol('X');
    player2.setSymbol('O');

    while (!gameFinished()) {
      makeMove();
      printBoard();
      changeCurrentPlayer();
    }

    calculateWinner();
  }

  private void calculateWinner() {
    String winner = board.getWinner();
    System.out.println(winner);
  }

  private void changeCurrentPlayer() {
    if (currentPlayer == player1) {
      currentPlayer = player2;
    } else {
      currentPlayer = player1;
    }
  }

  private void printBoard() {
    board.print();
  }

  // В этом методе данные вводятся с клавиатуры,
  // такой подход нужен для человека, но не для компьютера.
  // Перенесите эту функциональность в соответсвующую реализацию
  // игрока, а в этом методе вызывайте общий метод интерфейса Player
  private void makeMove() {
    currentPlayer.makeMove();
    board.fillCell(currentPlayer.getRow(), currentPlayer.getColumn(), currentPlayer.getSymbol());
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
