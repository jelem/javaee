package com.hillel.game.model.board;

public interface Board {

  void print();

  boolean isGameFinished();

  String getWinner();

  void fillCell(int row, int column, char currentPlayer);

}
