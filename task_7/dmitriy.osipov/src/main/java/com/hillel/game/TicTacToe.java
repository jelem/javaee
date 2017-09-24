package com.hillel.game;

public class TicTacToe {

  private Board board;

  private Player player1;
  private Player player2;

  private Player currentPlayer;

  public TicTacToe() {
  }

  public TicTacToe(Board board, Player player1, Player player2) {
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public void startGame() {
    currentPlayer = player1;
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
    System.out.println("Ходит " + currentPlayer.getName());
  }

  private void printBoard() {
    board.print();
  }

  private void makeMove() {
    currentPlayer.makeMove(board);
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
