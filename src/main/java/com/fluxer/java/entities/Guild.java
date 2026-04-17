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
}
