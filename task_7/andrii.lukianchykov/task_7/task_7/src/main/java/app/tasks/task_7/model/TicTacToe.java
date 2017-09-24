package app.tasks.task_7.model;


import app.tasks.task_7.model.Board.FixedBoard;
import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import app.tasks.task_7.model.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//Annotation based
/*@Component("tictaoe")*/
public class TicTacToe {
  private FixedBoard board;
  private Player player1;
  private Player player2;
  private Player currentPlayer;

  public TicTacToe(){}

  //Annotation based
  /*@Autowired
  public TicTacToe(@Qualifier("human") Player player1, @Qualifier("robot") Player player2,
      FixedBoard board) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = board;
  }
*/

  public TicTacToe(Player player1, Player player2, Player currentPlayer) {
    this.player1 = player1;
    this.player2 = player2;
    this.currentPlayer = currentPlayer;
  }

  public TicTacToe(HumanPlayer humanPlayer, PCPlayer robotPlayer, TicTacToe ticTacToeBoard) {
  }

  //Annotation based
  //Without spring-config.xml
  /*@Autowired
  @Qualifier("human")*/
  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }


  public void startGame() {
    while (!gameFinished()) {
      makeMove();
      printBoard();
      changeCurrentPlayer();
    }

    calculateWinner();
  }

  private void calculateWinner() {
    String winner = board.getWinner();
    System.out.println(winner);
  }

  private void changeCurrentPlayer() {
    if (currentPlayer == player1) {
      currentPlayer = player2;
    } else {
      currentPlayer = player1;
    }
  }

  private void printBoard() {
    board.showBoard();
  }

  // В этом методе данные вводятся с клавиатуры,
  // такой подход нужен для человека, но не для компьютера.
  // Перенесите эту функциональность в соответсвующую реализацию
  // игрока, а в этом методе вызывайте общий метод интерфейса Player
  private void makeMove() {
    int[] ints = currentPlayer.movement();
    board.fillCell(ints[0], ints[1], currentPlayer.getCharacter());
  }

  private boolean gameFinished() {
    return board.isGameFinished();
  }
}
