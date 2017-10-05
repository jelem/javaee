package com.hillel.game;

import com.hillel.game.model.board.Board;
import com.hillel.game.model.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// В этом классе необходимо заменить встроенные объекты
// на Dependency Injection с помощью Spring
@Service
public class TicTacToe {

  @Autowired
  private Board board;

  // В этой программе эти классы ничего не делают.
  // Вам нужно добавить 2 реализации игрока:
  //  - компьютер
  //  - человек
  @Autowired
  @Qualifier("human")
  private Player player1;
  @Autowired
  @Qualifier("pc")
  private Player player2;

  // Игрок, который в данный момент делает ход.
  // Тип данных должен быть Player и хранить
  // одного из игроков
  private Player currentPlayer;

  public TicTacToe(Board board, @Qualifier("human") Player player1,
      @Qualifier("pc") Player player2) {
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
  }

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
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
