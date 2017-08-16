package commands;

import services.VersionControlService;

import java.io.IOException;
import java.util.List;

public class LogCommand implements Command {

  @Override
  public boolean handle(VersionControlService controlService, List<String> params)
      throws IOException {
    if ("log".equals(params.get(0))) {
      System.out.println(controlService.log());
      return true;
    }
    return false;
  }
}
