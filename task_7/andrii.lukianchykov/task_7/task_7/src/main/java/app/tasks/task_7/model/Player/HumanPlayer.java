package app.tasks.task_7.model.Player;

import java.util.Scanner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//Annotation based
/*@Primary
@Repository("human")
//Without spring-config.xml
@Component("human")*/
public class HumanPlayer extends Player{

  private final Scanner scanner = new Scanner(System.in, "UTF-8");
  private int arrowCell = 0;
  private int columnCell = 0;
  private char symbol = 0;

  public HumanPlayer(){}
  public HumanPlayer(int arrowCell, int columnCell, char symbol) {
    this.arrowCell = arrowCell;
    this.columnCell = columnCell;
    this.symbol = symbol;
  }

  @Override
  public int[] movement() {

    System.out.println(" Make Move: ");
    String move = scanner.nextLine();
    //String move = scanner.next();

    //Ascii
    arrowCell = move.charAt(0) - 48;
    columnCell = move.charAt(1) - 48;

    return new int[]{arrowCell,columnCell};
  }

  @Override
  public char getCharacter() {
    return  getSymbol();
    //return symbol;
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
    return 'X';
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

}
