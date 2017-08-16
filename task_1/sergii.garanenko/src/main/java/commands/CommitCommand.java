package commands;

import services.VersionControlService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommitCommand implements Command {

  @Override
  public boolean handle(VersionControlService controlService, List<String> params)
      throws IOException {
    if ("commit".equals(params.get(0))) {
      if (params.size() > 1) {
        params.remove(0);
        controlService.commit(params.stream().collect(Collectors.joining(" ")));
      } else {
        System.out.println("incorrect command format");
      }
      return true;
    }
    return false;
  }
}