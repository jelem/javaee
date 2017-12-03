package app.tasks.task_7.AppConfigV2.service;

import app.tasks.task_7.AppConfigV2.repository.BoardRepository;
import app.tasks.task_7.model.Board.FixedBoard;
import java.util.List;

public class BoardServiceImpl implements BoardService {

  private BoardRepository boardRepository;

  public BoardServiceImpl() {
  }

  @Override
  public List<FixedBoard> getAllBoards() {
    return boardRepository.findAllBoards();
  }

  public BoardServiceImpl(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public void setBoardRepository(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

}
