package eu.macphail.amqclitoolset.cli.entity;

import java.util.Arrays;
import java.util.Optional;

public enum MessageType {
    TEXT("text"),
    BYTES("bytes");

    MessageType(String label) {
        this.label = label;
    }

    private final String label;

    public String getLabel() {
        return label;
    }

    public static MessageType getDefault() {
        return TEXT;
    }

    public static Optional<MessageType> wrap(String label) {
        return Arrays.stream(MessageType.values())
                .filter(mt -> mt.getLabel().toLowerCase().equals(label.toLowerCase()))
                .findFirst();
    }
}
