package app.tasks.task_7.AppConfigV2.repository;


import app.tasks.task_7.model.Board.FixedBoard;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository("board-repository")
public class BoardRepositoryImpl implements BoardRepository {

  @Override
  public List<FixedBoard> findAllBoards() {

    List<FixedBoard> listOfFixedBoards= new ArrayList<>();

    FixedBoard fixedBoard = new FixedBoard();
    FixedBoard fixedBoard2 = new FixedBoard();
    FixedBoard fixedBoard3 = new FixedBoard();

    listOfFixedBoards.add(fixedBoard);
    listOfFixedBoards.add(fixedBoard2);
    listOfFixedBoards.add(fixedBoard3);

    return listOfFixedBoards;
  }
}
