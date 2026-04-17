package com.fluxer.java.commands;

import com.fluxer.java.entities.Message;
import java.util.function.Consumer;

/**
 * Context for a command execution. 
 * Provides quick access to the message, author, and channel.
 */
public class CommandContext {
    private final Message message;
    private final String[] args;

    public CommandContext(Message message, String[] args) {
        this.message = message;
        this.args = args;
    }

    public Message getMessage() { return message; }
    public String[] getArgs() { return args; }
    
    /**
     * Fluent reply shortcut. 
     * Because every millisecond matters for a developer.
     */
    public void reply(String text) {
        message.reply(text);
    }
}
