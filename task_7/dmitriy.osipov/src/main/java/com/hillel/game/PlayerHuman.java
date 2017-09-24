package com.hillel.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@Component("player-human")
public class PlayerHuman extends Player {

  public PlayerHuman(@Value("${humanName}") String pname,
      @Value("${humanSign}") char psign) {
    super(pname, psign);
  }

  @Override
  public void makeMove(Board board) {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    int row;
    int column;
    boolean isMoveAllowed;
    do {
      System.out.print("Сделайте ваш ход: ");
      String move = scanner.next(); // Ход это координаты клетки, например "12"

      row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
      column = move.charAt(1) - 48; // номер столбца
      int[] position = {row, column};
      isMoveAllowed = board.getFreeCells()
          .stream().anyMatch(pos -> Arrays.equals(pos, position));
      if (!isMoveAllowed) {
        System.out.println("Ячейка занята. Сделайте другой ход! ");
      }
    }
    while (!isMoveAllowed);
    board.fillCell(row, column, sign);
  }
}
