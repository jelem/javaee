package com.hillel.game;

import java.util.Scanner;

public class PlayerHuman extends Player {

  public PlayerHuman(String pname, char psign) {
    super(pname, psign);
  }

  @Override
  public void makeMove(Board board) {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.print("Сделайте ваш ход: ");
    String move = scanner.next(); // Ход это координаты клетки, например "12"

    int row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
    int column = move.charAt(1) - 48; // номер столбца

    board.fillCell(row, column, sign);
  }
}
