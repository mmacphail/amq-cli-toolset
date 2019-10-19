package eu.macphail.amqclitoolset.cli.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Action {
    SEND("send");

    Action(String action) {
        this.action = action;
    }

    private final String action;

    public String getAction() {
        return action;
    }

    public static Optional<Action> wrap(String action) {
        return Arrays.stream(Action.values())
                .filter(mt -> mt.getAction().toLowerCase().equals(action.toLowerCase()))
                .findFirst();
    }
}
