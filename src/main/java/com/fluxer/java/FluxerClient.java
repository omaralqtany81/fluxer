package com.fluxer.java;

import com.fluxer.java.gateway.GatewayManager;
import com.fluxer.java.rest.RestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The heartbeat of the library. FluxerClient is the central hub through which
 * all interactions with the platform are coordinated.
 */
public class FluxerClient {
    private static final Logger logger = LoggerFactory.getLogger(FluxerClient.class);
    
    private final String token;
    private final GatewayManager gatewayManager;
    private final RestManager restManager;
    private final List<Object> listeners;

    FluxerClient(String token, List<Object> listeners) {
        this.token = token;
        this.listeners = listeners;
        this.restManager = new RestManager(this);
        this.gatewayManager = new GatewayManager(this);
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
}
