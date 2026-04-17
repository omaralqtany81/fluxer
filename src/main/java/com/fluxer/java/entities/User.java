package com.fluxer.java.entities;

public class User {
    private final String id;
    private final String username;
    private final String avatar;
    private final boolean bot;
    private final boolean captchaRequired;

    public User(String id, String username, String avatar, boolean bot, boolean captchaRequired) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.bot = bot;
        this.captchaRequired = captchaRequired;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isBot() {
        return bot;
    }

    public String getAsMention() {
        return "<@" + id + ">";
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", username='" + username + '\'' + '}';
    }
}
