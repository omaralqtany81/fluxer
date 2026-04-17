package com.fluxer.java;

import com.fluxer.java.gateway.GatewayManager;
import com.fluxer.java.rest.RestManager;
import com.fluxer.java.commands.CommandHandler;
import com.fluxer.java.leveling.LevelingManager;
import com.fluxer.java.events.EventListener;
import com.fluxer.java.utils.EntityParser;
import com.fluxer.java.interactivity.InteractivityManager;
import com.fluxer.java.broadcast.Broadcaster;
import com.fluxer.java.graphics.RankCardGenerator;
import com.fluxer.java.database.DatabaseManager;
import com.fluxer.java.events.EventBus;
import com.fluxer.java.plugins.PluginManager;
import com.fluxer.java.translate.TranslationManager;
import com.fluxer.java.automod.AutoModManager;
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
    private final EventBus eventBus;
    private final RankCardGenerator graphicEngine;
    private final DatabaseManager database;
    private final PluginManager pluginManager;
    private final TranslationManager translationManager;
    private final AutoModManager autoMod;
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
        this.eventBus = new EventBus();
        this.graphicEngine = new RankCardGenerator();
        this.database = new DatabaseManager("fluxer_data.json");
        this.pluginManager = new PluginManager(this);
        this.translationManager = new TranslationManager();
        this.autoMod = new AutoModManager();
    }

    public void login() {
        logger.info("Initializing fluxer.java client...");
        gatewayManager.connect();
    }

    public void registerListener(Object listener) {
        this.listeners.add(listener);
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

    public EventBus getEventBus() {
        return eventBus;
    }

    public RankCardGenerator getGraphicEngine() {
        return graphicEngine;
    }

    public DatabaseManager getDatabase() {
        return database;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public TranslationManager getTranslationManager() {
        return translationManager;
    }

    public AutoModManager getAutoMod() {
        return autoMod;
    }
}

