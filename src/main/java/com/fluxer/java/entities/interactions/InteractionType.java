package com.fluxer.java.entities.interactions;

/**
 * Types of interactions received from the Fluxer gateway.
 */
public enum InteractionType {
    PING(1),
    APPLICATION_COMMAND(2),
    MESSAGE_COMPONENT(3),
    APPLICATION_COMMAND_AUTOCOMPLETE(4),
    MODAL_SUBMIT(5);

    private final int id;
    InteractionType(int id) { this.id = id; }
    public int getId() { return id; }

    public static InteractionType fromId(int id) {
        for (InteractionType type : values()) {
            if (type.id == id) return type;
        }
        return PING;
    }
}
