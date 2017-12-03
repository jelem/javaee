package app.tasks.task_7;

import app.tasks.task_7.AppConfigV2.service.BoardService;
import app.tasks.task_7.AppConfigV2.service.PlayerService;
import app.tasks.task_7.model.Board.FixedBoard;
import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import app.tasks.task_7.model.TicTacToe;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

  public static void main(String[] args) {

    //FIRST
    ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");

    PlayerService playerService = appContext.getBean("player-service", PlayerService.class);
    BoardService boardService = appContext.getBean("board-service", BoardService.class);

    List<HumanPlayer> humanPlayers = playerService.getAllHumanPlayers();
    List<PCPlayer> pcPlayers = playerService.getAllPCPlayers();
    List<FixedBoard> fixedBoards = boardService.getAllBoards();

    //players.forEach(x -> System.out.println(x));
    humanPlayers.forEach(System.out::println);
    pcPlayers.forEach(System.out::println);
    fixedBoards.forEach(System.out::println);

    //SECOND
    //Annotation based
    //Without spring-config.xml
    /*ApplicationContext appContext1 = new ClassPathXmlApplicationContext("spring-config.xml");
    TicTacToe ticTacToe = appContext1.getBean("tictactoe", TicTacToe.class);
    ticTacToe.startGame();*/
  }
}