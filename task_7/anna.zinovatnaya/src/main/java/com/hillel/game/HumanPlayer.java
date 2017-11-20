package com.hillel.game;

import java.util.Scanner;

public class HumanPlayer implements Player {
  private String name;
  private char symbol;
  private int row;
  private  int column;

  public HumanPlayer() {
    this.name = "";
    this.symbol = ' ';
    this.row = 0;
    this.column = 0;
  }

  @Override
  public void makeMove() {
    Scanner scanner = new Scanner(System.in, "utf-8");
    System.out.print("Сделайте ваш ход: ");
    String move = scanner.next(); // Ход это координаты клетки, например "12"

    row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
    column = move.charAt(1) - 48; // номер столбца
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public char getSymbol() {
    return symbol;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }
}
