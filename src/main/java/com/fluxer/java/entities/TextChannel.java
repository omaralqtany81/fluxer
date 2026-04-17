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
    public void sendMessage(EmbedBuilder embed) {
        // Logic to convert embed to JSON and post
        // Using simple implementation for now
        ObjectNode data = mapper.createObjectNode();
        data.set("embed", mapper.valueToTree(embed));
        client.getRestManager().post("/channels/" + id + "/messages", data.toString());
    }
}
