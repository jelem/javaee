package com.hillel.game.model.player;

import com.hillel.game.model.board.Board;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class HumanPlayer implements Player {

  private String name;
  private char symbol;

  @Autowired
  private Board board;

  public HumanPlayer(String name, char symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  @Override
  public void makeMove() {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.print("Сделайте ваш ход: ");
    String move = scanner.next(); // Ход это координаты клетки, например "12"

    int row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
    int column = move.charAt(1) - 48; // номер столбца

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
