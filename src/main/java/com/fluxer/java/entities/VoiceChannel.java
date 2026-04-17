package com.fluxer.java.entities;

import com.fluxer.java.FluxerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a voice communication channel in Fluxer. 
 * Provides capabilities for dynamic management like bitrates and user limits.
 */
public class VoiceChannel {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String id;
    private final String name;
    private final FluxerClient client;

    public VoiceChannel(String id, String name, FluxerClient client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    /**
     * Dynamically updates the channel name.
     */
    public void setName(String newName) {
        ObjectNode data = mapper.createObjectNode().put("name", newName);
        client.getRestManager().post("/channels/" + id + "/edit", data.toString());
    }

    /**
     * Sets the maximum number of users allowed in this room. 
     * Essential for private temp channels.
     */
    public void setUserLimit(int limit) {
        ObjectNode data = mapper.createObjectNode().put("user_limit", limit);
        client.getRestManager().post("/channels/" + id + "/edit", data.toString());
    }

    /**
     * Deletes the channel. Used when the last person leaves the temp room.
     */
    public void delete() {
        client.getRestManager().post("/channels/" + id + "/delete", "{}");
    }
}
