package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandService {

  @Autowired
  private VersionControlService controlService;

  public void listen() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
            switch (commands.get(0)) {
              case "init":
                if (commands.size() > 1) {
                  controlService.init(commands.get(1));
                }
                break;
              case "commit":
                if (commands.size() > 1) {
                  commands.remove(0);
                  controlService.commit(commands.stream().collect(Collectors.joining(" ")));
                }
                break;
              case "log":
                System.out.println(controlService.log());
                break;
              case "revert":
                if (commands.size() > 1) {
                  controlService.revert(commands.get(1));
                }
                break;
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
