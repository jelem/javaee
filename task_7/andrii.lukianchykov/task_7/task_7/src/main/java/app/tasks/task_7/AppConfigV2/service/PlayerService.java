package app.tasks.task_7.AppConfigV2.service;

import app.tasks.task_7.AppConfigV2.repository.PlayerRepositoryImpl;
import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;

import java.util.List;


public interface PlayerService {


  public List<HumanPlayer> getAllHumanPlayers();
  public List<PCPlayer> getAllPCPlayers();

}
