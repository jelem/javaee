package services;

import commands.Command;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandService {

  @Autowired
  private VersionControlService controlService;
  private Command commandChain;

  public void addCommand(Command command) {
    commandChain = commandChain == null
        ? command
        : commandChain.or(command);
  }

  public void listen() {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in, StandardCharsets.UTF_8));

    while (true) {
      try {
        String line = reader.readLine();
        if (line != null) {
          if (line.startsWith("quit")) {
            break;
          }
          List<String> commands = Arrays.stream(line.split(" "))
              .filter(item -> item.length() != 0)
              .collect(Collectors.toList());
          if (commands.size() > 0) {
            if (!commandChain.handle(controlService, commands)) {
              System.out.println("Unknown command");
            }
          }
        }
      } catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Some IO error happend. Try again. Detailes shown above");
      }
    }
  }

}
