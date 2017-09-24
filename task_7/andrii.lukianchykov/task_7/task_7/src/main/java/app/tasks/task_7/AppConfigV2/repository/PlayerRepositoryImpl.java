package app.tasks.task_7.AppConfigV2.repository;

import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("player-repository")
public class PlayerRepositoryImpl implements PlayerRepository {

  @Override
  public List<HumanPlayer> findHumanPlayers() {

    List<HumanPlayer> humanPlayers= new ArrayList<>();

    HumanPlayer HumanPlayer = new HumanPlayer(1,1,'X');
    HumanPlayer HumanPlayer2 = new HumanPlayer(2,2,'*');
    HumanPlayer HumanPlayer3 = new HumanPlayer(3,3,'?');

    humanPlayers.add(HumanPlayer);
    humanPlayers.add(HumanPlayer2);
    humanPlayers.add(HumanPlayer3);

    return humanPlayers;
  }

  @Override
  public List<PCPlayer> findPCPlayers() {
    List<PCPlayer> pcPlayers= new ArrayList<>();

    PCPlayer PCPlayer = new PCPlayer(1,1,'0');
    PCPlayer PCPlayer2 = new PCPlayer(2,2,'_');
    PCPlayer PCPlayer3 = new PCPlayer(3,3,'.');

    pcPlayers.add(PCPlayer);
    pcPlayers.add(PCPlayer2);
    pcPlayers.add(PCPlayer3);

    return pcPlayers;
  }
}
