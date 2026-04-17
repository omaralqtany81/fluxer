package com.fluxer.java.entities;

import com.fluxer.java.FluxerClient;
import com.fluxer.java.utils.EmbedBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a text channel in Fluxer.
 */
public class TextChannel {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String id;
    private final String name;
    private final FluxerClient client;

    public TextChannel(String id, String name, FluxerClient client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Sends a message to this channel.
     */
    public void sendMessage(String content) {
        ObjectNode data = mapper.createObjectNode().put("content", content);
        client.getRestManager().post("/channels/" + id + "/messages", data.toString());
    }

    /**
     * Sends an embed to this channel.
     */
    public void sendMessage(com.fluxer.java.utils.EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        data.set("embeds", mapper.createArrayNode().add(mapper.valueToTree(embed)));
        client.getRestManager().post("/channels/" + id + "/messages", data.toString());
    }

    /**
     * Sends components (like buttons) to this channel. 
     * Even if Fluxer doesn't fully render them yet, your system is pushing the boundaries.
     */
    public void sendMessage(String content, com.fluxer.java.entities.components.ActionRow... rows) {
        ObjectNode data = mapper.createObjectNode().put("content", content);
        data.set("components", mapper.valueToTree(rows));
        client.getRestManager().post("/channels/" + id + "/messages", data.toString());
    }

    /**
     * Clears (bulk deletes) a specific number of messages in the channel.
     * Often used by moderation bots for cleaning up spam.
     */
    public void clearMessages(int amount) {
        ObjectNode data = mapper.createObjectNode().put("amount", amount);
        client.getRestManager().post("/channels/" + id + "/messages/bulk-delete", data.toString());
    }
}
