package com.fluxer.java;

import com.fluxer.java.gateway.GatewayManager;
import com.fluxer.java.rest.RestManager;
import com.fluxer.java.commands.CommandHandler;
import com.fluxer.java.leveling.LevelingManager;
import com.fluxer.java.events.EventListener;
import com.fluxer.java.utils.EntityParser;
import com.fluxer.java.interactivity.InteractivityManager;
import com.fluxer.java.broadcast.Broadcaster;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FluxerClient {
    private static final Logger logger = LoggerFactory.getLogger(FluxerClient.class);
    
    private final String token;
    private final GatewayManager gatewayManager;
    private final RestManager restManager;
    private final CommandHandler commandHandler;
    private final LevelingManager levelingManager;
    private final InteractivityManager interactivityManager;
    private final Broadcaster broadcaster;
    private final List<Object> listeners;

    FluxerClient(String token, List<Object> listeners, String prefix) {
        this.token = token;
        this.listeners = listeners;
        this.restManager = new RestManager(this);
        this.gatewayManager = new GatewayManager(this);
        this.commandHandler = new CommandHandler(prefix != null ? prefix : "!");
        this.levelingManager = new LevelingManager();
        this.interactivityManager = new InteractivityManager();
        this.broadcaster = new Broadcaster(this);
    }

    public void login() {
        logger.info("Initializing fluxer.java client...");
        gatewayManager.connect();
    }

    public String getToken() {
        return token;
    }

    public List<Object> getListeners() {
        return listeners;
    }

    public RestManager getRestManager() {
        return restManager;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public LevelingManager getLevelingManager() {
        return levelingManager;
    }
    public InteractivityManager getInteractivityManager() {
        return interactivityManager;
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }
}

