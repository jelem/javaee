package app.tasks.task_7.AppConfigV2.repository;

import app.tasks.task_7.model.Board.FixedBoard;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository("board-repository-second")
public class BoardRepositorySecondImpl implements BoardRepository {

  @Override
  public List<FixedBoard> findAllBoards() {
    return Collections.emptyList();
  }
}
