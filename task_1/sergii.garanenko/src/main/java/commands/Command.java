package commands;

import services.VersionControlService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface Command {

  public boolean handle(VersionControlService controlService, List<String> params)
      throws IOException;

  default Command or(Command other) {
    Objects.requireNonNull(other);
    return (controlService, params) -> handle(controlService, params) || other
        .handle(controlService, params);
  }
}
