package com.fluxer.java.entities;

import com.fluxer.java.FluxerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a server (Guild) in Fluxer. 
 * Provides deep management controls for administrative operations.
 */
public class Guild {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String id;
    private final String name;
    private final FluxerClient client;

    public Guild(String id, String name, FluxerClient client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    /**
     * Updates the guild's settings. 
     * Use "Deep Management" to reshape your server on the fly.
     */
    public void setName(String newName) {
        ObjectNode data = mapper.createObjectNode().put("name", newName);
        client.getRestManager().post("/guilds/" + id + "/edit", data.toString());
    }

    /**
     * Programmatically creates a new role.
     */
    public void createRole(String roleName, int color) {
        ObjectNode data = mapper.createObjectNode()
                .put("name", roleName)
                .put("color", color);
        client.getRestManager().post("/guilds/" + id + "/roles", data.toString());
    }

    /**
     * Deletes a role by its unique identifier.
     */
    public void deleteRole(String roleId) {
        client.getRestManager().post("/guilds/" + id + "/roles/" + roleId + "/delete", "{}");
    }

    public String getId() { return id; }
    public String getName() { return name; }

    // ==========================================
    // 🛡️ Moderation Toolkit (God-Tier Control)
    // ==========================================

    /**
     * Kicks a user from the guild.
     */
    public void kickMember(String userId, String reason) {
        ObjectNode data = mapper.createObjectNode().put("reason", reason);
        client.getRestManager().post("/guilds/" + id + "/members/" + userId + "/kick", data.toString());
    }

    /**
     * Bans a user from the guild, optionally specifying a duration and reason.
     */
    public void banMember(String userId, String reason, int daysToEraseMessages) {
        ObjectNode data = mapper.createObjectNode()
                .put("reason", reason)
                .put("delete_message_days", daysToEraseMessages);
        client.getRestManager().post("/guilds/" + id + "/bans/" + userId, data.toString());
    }

    /**
     * Timeouts (mutes) a user for a specific duration in seconds.
     */
    public void timeoutMember(String userId, long durationSeconds, String reason) {
        ObjectNode data = mapper.createObjectNode()
                .put("duration", durationSeconds)
                .put("reason", reason);
        client.getRestManager().post("/guilds/" + id + "/members/" + userId + "/timeout", data.toString());
    }

    /**
     * Issues a warning by assigning a specific "Warn" role to the user.
     */
    public void warnMember(String userId, String warnRoleId, String reason) {
        ObjectNode data = mapper.createObjectNode().put("reason", reason);
        client.getRestManager().post("/guilds/" + id + "/members/" + userId + "/roles/" + warnRoleId, data.toString());
    }
}
