package app.tasks.task_7.model.Player;

import java.util.Random;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//Annotation based
/*@Repository("pc")*/
public class PCPlayer extends Player{

  private final Scanner scanner = new Scanner(System.in, "UTF-8");
  private final int randNum = new Random().nextInt(2);
  private int arrowCell = 0;
  private int columnCell = 0;
  private char symbol = 0;

  public PCPlayer() {}
  public PCPlayer(int arrowCell, int columnCell, char symbol) {
    this.arrowCell = arrowCell;
    this.columnCell = columnCell;
    this.symbol = symbol;
  }

  @Override
  public int[] movement() {

    arrowCell = randNum;
    columnCell = randNum;

    System.out.println(" Pc made move,: [" + arrowCell +" ][ " + columnCell + " ][ " + "\n Your turn: ");

    return new int[]{arrowCell,columnCell};
  }

  @Override
  public char getCharacter() {
    return getSymbol();
  }

  public Scanner getScanner() {
    return scanner;
  }

  public int getArrowCell() {
    return arrowCell;
  }

  public void setArrowCell(int arrowCell) {
    this.arrowCell = arrowCell;
  }

  public int getColumnCell() {
    return columnCell;
  }

  public void setColumnCell(int columnCell) {
    this.columnCell = columnCell;
  }

  public char getSymbol() {
    return '0';
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }
}
