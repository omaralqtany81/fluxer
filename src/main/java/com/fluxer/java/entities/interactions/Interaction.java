package com.fluxer.java.entities.interactions;

import com.fluxer.java.FluxerClient;
import com.fluxer.java.entities.Member;
import com.fluxer.java.entities.User;

/**
 * Base class for all Gateway interactions (Slash Commands, Components).
 */
public abstract class Interaction {
    protected final String id;
    protected final String token;
    protected final InteractionType type;
    protected final Member member;
    protected final User user;
    protected final FluxerClient client;

    public Interaction(String id, String token, InteractionType type, Member member, User user, FluxerClient client) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.member = member;
        this.user = user;
        this.client = client;
    }

    public void reply(String content) {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.node.ObjectNode body = mapper.createObjectNode();
        body.put("type", 4);
        body.set("data", mapper.createObjectNode().put("content", content));
        client.getRestManager().post("/interactions/" + id + "/" + token + "/callback", body.toString());
    }

    public void reply(com.fluxer.java.utils.EmbedBuilder embed) {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.node.ObjectNode body = mapper.createObjectNode();
        body.put("type", 4);
        com.fasterxml.jackson.databind.node.ObjectNode data = mapper.createObjectNode();
        data.set("embeds", mapper.createArrayNode().add(mapper.valueToTree(embed)));
        body.set("data", data);
        client.getRestManager().post("/interactions/" + id + "/" + token + "/callback", body.toString());
    }

    public String getId() { return id; }
    public InteractionType getType() { return type; }
    public FluxerClient getClient() { return client; }
}
