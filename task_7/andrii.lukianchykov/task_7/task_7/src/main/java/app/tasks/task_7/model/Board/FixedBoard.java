package app.tasks.task_7.model.Board;

import javax.xml.ws.ServiceMode;
import org.springframework.stereotype.Service;

//Annotation based
/*@Service("tictaoe-board")*/
public class FixedBoard extends Board{

  private char[][] array;
  private char winner;
  private int maxSize = 0;

  public FixedBoard(int maxSize, char[][] array, char winner) {
    this.array = array;
    this.winner = winner;
    this.maxSize = maxSize;
  }

  public FixedBoard(int maxSize) {
    for (int i = 0; i < maxSize; i++) {
      for (int j = 0; j < maxSize; j++) {
        array[i][j] = ' '; // Заносим пробел в каждую ячейку массива
      }
    }
    this.maxSize = maxSize;
  }

  public FixedBoard() {
    for (int i = 0; i < maxSize; i++) {
      for (int j = 0; j < maxSize; j++) {
        array[i][j] = ' '; // Заносим пробел в каждую ячейку массива
      }
    }
  }

  public void showBoard() {
    for (int i = 0; i < maxSize; i++) {
      System.out.println("-------");
      for (int j = 0; j < maxSize; j++) {
        System.out.print("|");
        System.out.print(array[i][j]);
      }
      System.out.println("|");
    }
    System.out.println("-------");
  }

  public void fillCell(int row, int column, char currentPlayerSymbol) {
    array[row][column] = currentPlayerSymbol;
  }

  // Метод работает только для первой строки нашего поля.
  // Подумайте как можно его доделать для всех горизонталей,
  // вертикалей и диагоналей.
  // Может быть его как-то можно упростить? Например, с помощью циклов.
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

  public String getWinner() {
    if (winner == 'X') {
      return "X wins";
    } else {
      return "0 wins";
    }
  }

  public char[][] getArray() {
    return array;
  }

  public void setArray(char[][] array) {
    this.array = array;
  }

  public void setWinner(char winner) {
    this.winner = winner;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }
}
