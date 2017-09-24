package app.tasks.task_7.AppConfigV2.repository;


import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import java.util.List;

public interface PlayerRepository {

  public List<HumanPlayer> findHumanPlayers();
  public List<PCPlayer> findPCPlayers();
}
