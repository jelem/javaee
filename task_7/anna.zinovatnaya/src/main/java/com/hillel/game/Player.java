package com.hillel.game;

public interface Player {
  void makeMove();

  int getRow();

  int getColumn();

  char getSymbol();

  void setName(String name);

  void setSymbol(char symbol);
}
