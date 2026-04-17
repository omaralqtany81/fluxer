package com.fluxer.java.entities.interactions;

import com.fluxer.java.FluxerClient;
import com.fluxer.java.entities.Member;
import com.fluxer.java.entities.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Specifically handles Application Commands (Slash Commands).
 */
public class SlashCommandInteraction extends Interaction {
    private final String commandName;
    private final Map<String, Object> options = new HashMap<>();

    public SlashCommandInteraction(String id, String token, Member member, User user, String commandName, FluxerClient client) {
        super(id, token, InteractionType.APPLICATION_COMMAND, member, user, client);
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void addOption(String name, Object value) {
        this.options.put(name, value);
    }

    public Object getOption(String name) {
        return options.get(name);
    }
}
