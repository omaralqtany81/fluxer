package com.fluxer.java.broadcast;

import com.fluxer.java.FluxerClient;
import com.fluxer.java.utils.EmbedBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Broadcaster {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final FluxerClient client;

    public Broadcaster(FluxerClient client) {
        this.client = client;
    }

    public void sendDirectMessage(String userId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));
        client.getRestManager().post("/users/" + userId + "/dms", data.toString());
    }

    public void broadcastToRole(String guildId, String roleId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));
        data.put("target_role", roleId);
        client.getRestManager().post("/guilds/" + guildId + "/broadcast", data.toString());
    }

    public void broadcastToServer(String targetChannelId, String content, EmbedBuilder embed) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));
        client.getRestManager().post("/channels/" + targetChannelId + "/messages", data.toString());
    }
}
