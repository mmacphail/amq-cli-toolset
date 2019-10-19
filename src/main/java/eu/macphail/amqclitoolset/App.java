package eu.macphail.amqclitoolset;

import eu.macphail.amqclitoolset.cli.boundary.CliEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@Slf4j
@EnableJms
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private CliEntryPoint cliEntryPoint;

    @Override
    public void run(String... args) throws Exception {
        cliEntryPoint.run(args);
        SpringApplication.exit(context);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
