package com.hillel.game.model.player;

import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository("robot")
public class RobotPlayer extends Player {



  @Override
  public int[] makeMove() {

    int row = new Random().nextInt(2);
    int column = new Random().nextInt(2); // номер столбца
    System.out.print("Робот сделал ход! : " + row + "  " + column);

    return new int[]{row, column};
  }

  @Override
  public char getSymbol() {
    return '0';
  }
}
