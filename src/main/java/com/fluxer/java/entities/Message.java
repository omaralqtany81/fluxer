package com.fluxer.java.entities;

public class Message {
    private final String id;
    private final String content;
    private final TextChannel channel;
    private final User author;

    public Message(String id, String content, TextChannel channel, User author) {
        this.id = id;
        this.content = content;
        this.channel = channel;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public User getAuthor() {
        return author;
    }

    /**
     * Quickly replies to this message.
     */
    public void reply(String text) {
        channel.sendMessage(text);
    }

    @Override
    public String toString() {
        return "Message{" + "id='" + id + '\'' + ", author=" + author + ", content='" + content + '\'' + '}';
    }
}
