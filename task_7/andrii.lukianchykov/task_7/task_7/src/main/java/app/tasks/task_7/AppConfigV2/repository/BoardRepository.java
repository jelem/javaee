package app.tasks.task_7.AppConfigV2.repository;

import app.tasks.task_7.model.Board.FixedBoard;
import java.util.List;

public interface BoardRepository {

  public List<FixedBoard> findAllBoards();

}
