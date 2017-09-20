package com.hillel.game.config;

import com.hillel.game.TicTacToe;
import com.hillel.game.model.board.BoardImpl;
import com.hillel.game.model.player.HumanPlayer;
import com.hillel.game.model.player.PcPlayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.hillel.game"})
public class ApplicationConfig {

  @Bean
  public BoardImpl getBoardImpl() {
    return new BoardImpl();
  }

  @Bean(name = "human")
  public HumanPlayer getHumanPlayer() {
    return new HumanPlayer("Human", 'X');
  }

  @Bean(name = "pc")
  public PcPlayer getPcPlayer() {
    return new PcPlayer("PC", 'O');
  }

  public TicTacToe getTicTacToe() {
    return new TicTacToe(getBoardImpl(), getHumanPlayer(), getPcPlayer());
  }
}
