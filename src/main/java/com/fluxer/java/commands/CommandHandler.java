package com.fluxer.java.commands;

import com.fluxer.java.entities.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandHandler {
    private final Map<String, Consumer<CommandContext>> commands = new HashMap<>();
    private final String prefix;

    public CommandHandler(String prefix) {
        this.prefix = prefix;
    }

    public void register(String name, Consumer<CommandContext> action) {
        commands.put(name.toLowerCase(), action);
    }

    public void handle(Message message) {
        String content = message.getContent();
        if (!content.startsWith(prefix)) return;

        String[] parts = content.substring(prefix.length()).split("\\s+");
        String commandName = parts[0].toLowerCase();

        if (commands.containsKey(commandName)) {
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
            commands.get(commandName).accept(new CommandContext(message, args));
        }
    }
}
