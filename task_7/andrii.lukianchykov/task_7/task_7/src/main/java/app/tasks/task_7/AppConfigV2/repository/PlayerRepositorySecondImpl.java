package app.tasks.task_7.AppConfigV2.repository;

import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Primary
@Repository("player-repository-second")
public class PlayerRepositorySecondImpl implements PlayerRepository {

  /*@Override
  public List<Player> findPlayers() {
    return Collections.emptyList();
  }*/

  @Override
   public List<HumanPlayer> findHumanPlayers() {
    return Collections.emptyList();
  }

  @Override
  public List<PCPlayer> findPCPlayers() {
    return Collections.emptyList();
  }

}


