package com.hillel.game;

import java.util.Random;
import java.util.Scanner;

public class ComputerPlayer implements Player {
  private String name;
  private char symbol;
  private int row;
  private  int column;

  public ComputerPlayer() {
    this.name = "";
    this.symbol = ' ';
    this.row = 0;
    this.column = 0;
  }

  @Override
  public void makeMove() {
    Random randomGenerator = new Random();
    row = randomGenerator.nextInt(3);
    column = randomGenerator.nextInt(3);
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
