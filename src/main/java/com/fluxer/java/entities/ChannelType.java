package com.fluxer.java.entities;

/**
 * Defines the possible channel types within the Fluxer platform.
 */
public enum ChannelType {
    TEXT(0),
    VOICE(2),
    CATEGORY(4),
    LINK(5); // Fluxer specific: Holds a direct URL

    private final int id;

    ChannelType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ChannelType fromId(int id) {
        for (ChannelType type : values()) {
            if (type.id == id) return type;
        }
        return TEXT;
    }
}
