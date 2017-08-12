
import commands.CommitCommand;
import commands.InitCommand;
import commands.LogCommand;
import commands.RevertCommand;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import services.CommandService;

@SpringBootConfiguration
@ComponentScan({"services", "model", "utils"})
public class Config {

  @Bean
  public PropertyPlaceholderConfigurer getPPC() {
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
    propertyPlaceholderConfigurer
        .setLocation(new ClassPathResource("VCS.properties"));
    return propertyPlaceholderConfigurer;
  }

  @Bean
  public CommandService getCommandService() {
    CommandService commandService = new CommandService();
    commandService.addCommand(new InitCommand());
    commandService.addCommand(new RevertCommand());
    commandService.addCommand(new LogCommand());
    commandService.addCommand(new CommitCommand());
    return commandService;
  }
}
