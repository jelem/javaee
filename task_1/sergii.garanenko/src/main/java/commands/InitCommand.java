package commands;

import services.VersionControlService;

import java.io.IOException;
import java.util.List;

public class InitCommand implements Command {

  @Override
  public boolean handle(VersionControlService controlService, List<String> params)
      throws IOException {
    if ("init".equals(params.get(0))) {
      if (params.size() > 1) {
        controlService.init(params.get(1));
      } else {
        System.out.println("incorrect command format");
      }
      return true;
    }
    return false;
  }
}