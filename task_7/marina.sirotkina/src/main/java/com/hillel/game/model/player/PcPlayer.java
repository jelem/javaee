package com.hillel.game.model.player;

import com.hillel.game.model.board.Board;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class PcPlayer implements Player {

  private String name;
  private char symbol;

  @Autowired
  private Board board;

  public PcPlayer(String name, char symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  @Override
  public void makeMove() {
    System.out.print("Ходит противник: ");

    int row = new Random().nextInt(2); // Получаем код символа из таблицы ASCII (это номер строки)
    int column = new Random().nextInt(2); // номер столбца

    board.fillCell(row, column, symbol);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getSymbol() {
    return symbol;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }
}
