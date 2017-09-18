package com.hillel.game;

import java.util.Scanner;

// В этом классе необходимо заменить встроенные объекты
// на Dependency Injection с помощью Spring
public class TicTacToe {

  private Board board = new Board();

  // В этой программе эти классы ничего не делают.
  // Вам нужно добавить 2 реализации игрока:
  //  - компьютер
  //  - человек
  private Player player1 = new Player("John");
  private Player player2 = new Player("Tony");

  // Игрок, который в данный момент делает ход.
  // Тип данных должен быть Player и хранить
  // одного из игроков
  private char currentPlayer = 'X';

  public void startGame() {
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
    if (currentPlayer == 'X') {
      currentPlayer = 'O';
    } else {
      currentPlayer = 'X';
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
    Scanner scanner = new Scanner(System.in);
    System.out.print("Сделайте ваш ход: ");
    String move = scanner.next(); // Ход это координаты клетки, например "12"

    int row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
    int column = move.charAt(1) - 48; // номер столбца

    board.fillCell(row, column, currentPlayer);
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
