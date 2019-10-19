package eu.macphail.amqclitoolset.cli.boundary;

import eu.macphail.amqclitoolset.amq.boundary.SendMessageService;
import eu.macphail.amqclitoolset.cli.control.Parameters;
import eu.macphail.amqclitoolset.cli.entity.Action;
import eu.macphail.amqclitoolset.cli.entity.MessageType;
import eu.macphail.amqclitoolset.metadata.boundary.AppMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class CliEntryPoint {

    @Autowired
    private AppMetadata appMetadata;

    @Autowired
    private SendMessageService sendMessageService;

    public void run(String... args) throws ParseException, IOException {
        log.info(appMetadata.getName() + " " + appMetadata.getVersion());
        CommandLineParser parser = new DefaultParser();
        CommandLine helpParam = parser.parse(Parameters.HELP_OPTIONS, args, true);

        if (helpParam.hasOption("help")) {
            printHelp();
        } else {
            try {
                CommandLine parameters = parser.parse(Parameters.APP_OPTIONS, args);
                runAction(parameters);
            } catch(MissingOptionException e) {
                printHelp(e.getLocalizedMessage());
            }
        }
    }

    private void printHelp() {
        printHelp(null);
    }

    private void printHelp(String diagnostic) {
        String header = Optional.ofNullable(diagnostic).map(d -> "\n" + d).orElse("");
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(appMetadata.getName(), header, Parameters.APP_OPTIONS, "", true);
    }

    private void runAction(CommandLine parameters) throws IOException {
        String actionLabel = parameters.getOptionValue("action");
        Optional<Action> action = Action.wrap(actionLabel);

        if (action.isPresent()) {
            switch(action.get()) {
                case SEND:
                    runSendAction(parameters);
                    break;
            }
        } else {
            log.error("Action " + action + " unsupported.");
            printHelp();
        }
    }

    private void runSendAction(CommandLine parameters) throws IOException {
        String messageTypeLabel = parameters.getOptionValue("type", MessageType.getDefault().getLabel());
        Optional<MessageType> messageType = MessageType.wrap(
                parameters.getOptionValue("type", MessageType.TEXT.getLabel()));

        String messagePayloadPath = parameters.getOptionValue("payload");
        String destination = parameters.getOptionValue("destination");

        if (messageType.isPresent()) {
            log.info("Using the send action");
            sendMessageService.sendMessage(destination, messageType.get(), messagePayloadPath);
        } else {
            log.error("Message type " + messageTypeLabel + " unsupported.");
        }
    }

}
