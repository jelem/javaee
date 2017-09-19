package com.hillel.game.model.player;

import java.util.Scanner;

public class HumanPlayer extends Player {



  @Override
  public int[] makeMove() {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.print("Сделайте ваш ход: ");
    String move = scanner.next(); // Ход это координаты клетки, например "12"

    int row = move.charAt(0) - 48; // Получаем код символа из таблицы ASCII (это номер строки)
    int column = move.charAt(1) - 48; // номер столбца

    return new int[]{row, column};
  }

  @Override
  public char getSymbol() {
    return 'X';
  }
}
