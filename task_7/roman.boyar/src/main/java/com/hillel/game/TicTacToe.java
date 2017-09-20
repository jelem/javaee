package com.hillel.game;

import com.hillel.game.model.board.TicTacToeBoard;
import com.hillel.game.model.player.Player;

public class TicTacToe {

  private TicTacToeBoard board;
  private Player player1;
  private Player player2;
  private Player currentPlayer;

  public TicTacToe(Player player1, Player player2, TicTacToeBoard board) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = board;
  }


  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
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
    int[] ints = currentPlayer.makeMove();
    board.fillCell(ints[0], ints[1], currentPlayer.getSymbol());
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
