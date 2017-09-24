package app.tasks.task_7.AppConfigV1;

import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import app.tasks.task_7.model.TicTacToe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Without spring-config.xml
@Configuration
@ComponentScan({"app.tasks.task_7"})
public class AppConfig1 {

  @Bean
  public PCPlayer getRobotPlayer() {
    return new PCPlayer();
  }

  @Bean
  public HumanPlayer getHumanPlayer() {
    return new HumanPlayer();
  }

  @Bean
  public TicTacToe getTicTacToeBoard() {
    return new TicTacToe();
  }

  @Bean
  public TicTacToe getTicTacToe() {
    return new TicTacToe(getHumanPlayer(), getRobotPlayer(), getTicTacToeBoard());
  }
}
