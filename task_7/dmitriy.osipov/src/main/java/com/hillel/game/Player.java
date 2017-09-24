package com.hillel.game;

public abstract class Player {

  private String name;
  protected char sign;

  public Player(String pname, char psign) {
    name = pname;
    sign = psign;
  }

  public String getName() {
    return name;
  }

  public abstract void makeMove(Board board);
}
