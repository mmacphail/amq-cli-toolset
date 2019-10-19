package eu.macphail.amqclitoolset.cli.control;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Parameters {

    public static final Options HELP_OPTIONS = getHelpOptions();

    public static final Options APP_OPTIONS = getAppOptions();

    private static Options getHelpOptions() {
        final Option usage = Option.builder("h")
                .longOpt("help")
                .desc("Print this help.")
                .required(false)
                .build();

        Options options = new Options();
        options.addOption(usage);

        return options;
    }

    private static Options getAppOptions() {
        final Option action = Option.builder("a")
                .longOpt("action")
                .desc("Action: sends a message with 'send'.")
                .hasArg(true)
                .argName("action")
                .required(true)
                .build();

        final Option messageType = Option.builder("t")
                .longOpt("type")
                .desc("The type of the message: can be 'text' or 'bytes'. Default is 'text'. Bytes will be encoded to utf-8.")
                .hasArg(true)
                .argName("type")
                .required(false)
                .build();

        final Option messagePayload = Option.builder("p")
                .longOpt("payload")
                .desc("The file containing the message payload. The file format must be utf-8.")
                .hasArg(true)
                .argName("payload")
                .required(true)
                .build();

        final Option destination = Option.builder("d")
                .longOpt("destination")
                .desc("The name of the destination.")
                .hasArg(true)
                .argName("destination")
                .required(true)
                .build();

        Options options = new Options();

        mergeAllOptions(options);

        options.addOption(action);
        options.addOption(messageType);
        options.addOption(messagePayload);
        options.addOption(destination);

        return options;
    }

    private static void mergeAllOptions(Options options) {
        HELP_OPTIONS.getOptions().forEach(options::addOption);
    }
}
