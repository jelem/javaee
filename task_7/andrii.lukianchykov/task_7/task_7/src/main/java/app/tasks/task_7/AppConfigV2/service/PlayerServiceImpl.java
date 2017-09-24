package app.tasks.task_7.AppConfigV2.service;



import app.tasks.task_7.AppConfigV2.repository.PlayerRepository;
import app.tasks.task_7.model.Player.HumanPlayer;
import app.tasks.task_7.model.Player.PCPlayer;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

  private PlayerRepository playerRepository;

  public PlayerServiceImpl() {
  }

  @Override
  public List<HumanPlayer> getAllHumanPlayers() {
    return playerRepository.findHumanPlayers();
  }

  @Override
  public List<PCPlayer> getAllPCPlayers() {
    return playerRepository.findPCPlayers();
  }

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void setPlayerRepository(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

}
