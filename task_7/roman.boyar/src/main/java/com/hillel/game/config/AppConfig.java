package com.hillel.game.config;


import com.hillel.game.TicTacToe;
import com.hillel.game.model.board.TicTacToeBoard;
import com.hillel.game.model.player.HumanPlayer;
import com.hillel.game.model.player.RobotPlayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.hillel.game"})
public class AppConfig {

  @Bean
  public RobotPlayer getRobotPlayer() {
    return new RobotPlayer();
  }

  @Bean
  public HumanPlayer getHumanPlayer() {
    return new HumanPlayer();
  }

  @Bean
  public TicTacToeBoard getTicTacToeBoard() {
    return new TicTacToeBoard();
  }

  @Bean
  public TicTacToe getTicTacToe() {
    return new TicTacToe(getHumanPlayer(), getRobotPlayer(), getTicTacToeBoard());
  }

}
