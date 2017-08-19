import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import services.CommandService;

public class Main {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    CommandService commandService = context.getBean(CommandService.class);
    new Thread(() -> {
      commandService.listen();
    }).start();
  }
}
