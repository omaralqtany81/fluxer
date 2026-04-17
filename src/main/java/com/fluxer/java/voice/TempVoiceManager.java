package com.fluxer.java.voice;

import com.fluxer.java.entities.VoiceChannel;
import com.fluxer.java.FluxerClient;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the lifecycle of temporary voice rooms. 
 * Automatically tracks which user owns which room.
 */
public class TempVoiceManager {
    private final String joinToCreateId;
    private final Map<String, String> userRooms = new HashMap<>();
    private final FluxerClient client;

    public TempVoiceManager(String joinToCreateId, FluxerClient client) {
        this.joinToCreateId = joinToCreateId;
        this.client = client;
    }

    /**
     * Logic for when a user joins the master channel. 
     * In a real scenario, this would be triggered by an event.
     */
    public void handleJoin(String userId, String username) {
        // 1. Create the new Voice Channel via API
        // 2. Move the user (if platform supports it)
        // 3. Send the Dashboard
        System.out.println("Generating temp room for " + username);
    }

    public String getJoinToCreateId() {
        return joinToCreateId;
    }
}
