package com.hillel.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("player-pc")
public class PlayerPc extends Player {

  public PlayerPc(@Value("${pcName}") String pname,
      @Value("${pcSign}") char psign) {
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
