package com.fluxer.java.entities;

/**
 * All available permissions in the Fluxer platform. 
 * Use these to check if a user or the bot has authority to perform specific actions.
 */
public enum Permission {
    CREATE_INSTANT_INVITE(1L << 0),
    KICK_MEMBERS(1L << 1),
    BAN_MEMBERS(1L << 2),
    ADMINISTRATOR(1L << 3),
    MANAGE_CHANNELS(1L << 4),
    MANAGE_GUILD(1L << 5),
    ADD_REACTIONS(1L << 6),
    VIEW_AUDIT_LOG(1L << 7),
    VIEW_CHANNEL(1L << 10),
    SEND_MESSAGES(1L << 11),
    MANAGE_MESSAGES(1L << 13),
    EMBED_LINKS(1L << 14),
    ATTACH_FILES(1L << 15),
    READ_MESSAGE_HISTORY(1L << 16),
    MENTION_EVERYONE(1L << 17),
    USE_EXTERNAL_EMOJIS(1L << 18),
    CONNECT(1L << 20),
    SPEAK(1L << 21),
    MUTE_MEMBERS(1L << 22),
    DEAFEN_MEMBERS(1L << 23),
    MOVE_MEMBERS(1L << 24),
    USE_VAD(1L << 25),
    CHANGE_NICKNAME(1L << 26),
    MANAGE_NICKNAMES(1L << 27),
    MANAGE_ROLES(1L << 28),
    MANAGE_WEBHOOKS(1L << 29),
    MANAGE_EMOJIS_AND_STICKERS(1L << 30);

    private final long offset;

    Permission(long offset) {
        this.offset = offset;
    }

    public long getOffset() {
        return offset;
    }

    /**
     * Helper to check if a raw bitfield value contains this permission.
     */
    public boolean hasPermission(long permissions) {
        return (permissions & offset) == offset;
    }
}
