package com.hillel.game.model.board;

public class BoardImpl implements Board {

  private char[][] array = new char[3][3];

  private char winner;

  public BoardImpl() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        array[i][j] = ' '; // Заносим пробел в каждую ячейку массива
      }
    }
  }

  @Override
  public void print() {
    for (int i = 0; i < 3; i++) {
      System.out.println("-------");
      for (int j = 0; j < 3; j++) {
        System.out.print("|");
        System.out.print(array[i][j]);
      }
      System.out.println("|");
    }
    System.out.println("-------");
  }

  @Override
  public boolean isGameFinished() {
    if (array[0][0] == array[0][1]
        && array[0][1] == array[0][2]
        && array[0][2] == 'X') {
      winner = 'X'; // Здесь побеждают крестики
      return true;
    }

    if (array[0][0] == array[0][1]
        && array[0][1] == array[0][2]
        && array[0][2] == 'O') {
      winner = 'O'; // Здесь побеждают нолики
      return true;
    }
    return false;
  }

  @Override
  public String getWinner() {
    if (winner == 'X') {
      return "Крестики одержали победу";
    } else {
      return "Нолики одержали победу";
    }
  }

  @Override
  public void fillCell(int row, int column, char currentPlayer) {
    array[row][column] = currentPlayer;
  }
}
