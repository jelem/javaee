package com.hillel.game.model.player;

public abstract class Player {

  private String name;

  public Player(String pname) {
    name = pname;
  }

  public abstract int[] makeMove();

  public abstract char getSymbol();
}
