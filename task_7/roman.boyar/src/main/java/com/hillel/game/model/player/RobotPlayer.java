package com.hillel.game.model.player;

public class RobotPlayer extends Player {

  public RobotPlayer(String pname) {
    super(pname);
  }

  @Override
  public int[] makeMove() {

    int row = (int) (Math.random() * 3);
    int column = (int) (Math.random() * 3); // номер столбца
    System.out.print("Робот сделал ход! : " + row + "  " + column);

    return new int[]{row, column};
  }

  @Override
  public char getSymbol() {
    return '0';
  }
}
