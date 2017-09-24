package com.hillel.game;

import java.util.List;
import java.util.Random;

public class PlayerPc extends Player {

  public PlayerPc(String pname, char psign) {
    super(pname, psign);
  }

  @Override
  public void makeMove(Board board) {
    List<int[]> freeCells = board.getFreeCells();
    int[] selectedCell;
    Random random = new Random();
    selectedCell = freeCells.get(random.nextInt(freeCells.size()));
    board.fillCell(selectedCell[0], selectedCell[1], sign);
  }
}
