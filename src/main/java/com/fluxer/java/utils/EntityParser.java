package com.fluxer.java.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fluxer.java.entities.Message;
import com.fluxer.java.entities.User;

/**
 * Internal utility to transform raw JSON signals from the gateway into 
 * strongly-typed library entities.
 */
public class EntityParser {
    
    public static User parseUser(JsonNode data) {
        return new User(
            data.get("id").asText(),
            data.get("username").asText(),
            data.has("avatar") && !data.get("avatar").isNull() ? data.get("avatar").asText() : null,
            data.has("bot") && data.get("bot").asBoolean(),
            data.has("captcha_required") && data.get("captcha_required").asBoolean()
        );
    }

    public static com.fluxer.java.entities.LinkChannel parseLinkChannel(JsonNode data, com.fluxer.java.FluxerClient client) {
        return new com.fluxer.java.entities.LinkChannel(
            data.get("id").asText(),
            data.get("name").asText(),
            data.get("url").asText(),
            client
        );
    }

    public static com.fluxer.java.entities.interactions.Interaction parseInteraction(JsonNode data, com.fluxer.java.FluxerClient client) {
        int type = data.get("type").asInt();
        String id = data.get("id").asText();
        String token = data.get("token").asText();
        
        // Simplified member/user logic
        com.fluxer.java.entities.User user = parseUser(data.get("user"));
        
        if (type == 2) { // Application Command
            return new com.fluxer.java.entities.interactions.SlashCommandInteraction(
                id, token, null, user, data.get("data").get("name").asText(), client
            );
        }
        return null; 
    }

    public static Message parseMessage(JsonNode data, com.fluxer.java.FluxerClient client) {
        return new Message(
            data.get("id").asText(),
            data.get("content").asText(),
            new com.fluxer.java.entities.TextChannel(data.get("channel_id").asText(), "channel", client),
            parseUser(data.get("author"))
        );
    }
}
