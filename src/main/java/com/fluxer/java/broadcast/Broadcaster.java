package com.fluxer.java.broadcast;

import com.fluxer.java.FluxerClient;
import com.fluxer.java.utils.EmbedBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Enterprise Mass-Messaging Engine.
 * Supports sending direct messages (DMs), cross-server broadcasting, and role-targeted announcements.
 */
public class Broadcaster {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final FluxerClient client;

    public Broadcaster(FluxerClient client) {
        this.client = client;
    }

    /**
     * Sends a Direct Message (Private Message) to a specific user.
     * Supports rich Embeds.
     */
    public void sendDirectMessage(String userId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));

        // Assuming API has a /users/{id}/dm endpoint or similar.
        client.getRestManager().post("/users/" + userId + "/dms", data.toString());
    }

    /**
     * Broadcasts a message to all members holding a specific role within a server.
     */
    public void broadcastToRole(String guildId, String roleId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));
        data.put("target_role", roleId);

        client.getRestManager().post("/guilds/" + guildId + "/broadcast", data.toString());
    }

    /**
     * Broadcasts an announcement to another server's specific channel.
     */
    public void broadcastToServer(String targetChannelId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));

        client.getRestManager().post("/channels/" + targetChannelId + "/messages", data.toString());
    }
}
