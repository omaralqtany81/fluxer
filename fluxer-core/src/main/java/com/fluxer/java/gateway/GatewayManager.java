package com.fluxer.java.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxer.java.FluxerClient;
import com.fluxer.java.entities.Message;
import com.fluxer.java.utils.EntityParser;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GatewayManager extends WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger(GatewayManager.class);
    private static final String GATEWAY_URL = "wss://gateway.fluxer.app/?v=1";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final FluxerClient client;
    private WebSocket webSocket;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean connected = false;

    public GatewayManager(FluxerClient client) {
        this.client = client;
    }

    public void connect() {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(GATEWAY_URL).build();
        this.webSocket = httpClient.newWebSocket(request, this);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        logger.info("Gateway connection opened. Identifying...");
        this.connected = true;
        identify();
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        try {
            JsonNode payload = objectMapper.readTree(text);
            int op = payload.get("op").asInt();
            String event = payload.has("t") ? payload.get("t").asText() : null;
            handlePayload(op, event, payload.get("d"));
        } catch (Exception e) {
            logger.error("Error parsing gateway message: ", e);
        }
    }

    private void handlePayload(int op, String event, JsonNode data) {
        switch (op) {
            case 0:
                if ("MESSAGE_CREATE".equals(event)) {
                    Message msg = EntityParser.parseMessage(data, client);
                    client.getCommandHandler().handle(msg);
                    client.getLevelingManager().processMessage(msg);
                    client.getEventBus().post(msg, client.getListeners());
                } else if ("READY".equals(event)) {
                    logger.info("Bot is ready!");
                    client.getEventBus().post(new com.fluxer.java.events.ReadyEvent(), client.getListeners());
                }
                break;
            case 10:
                long heartbeatInterval = data.get("heartbeat_interval").asLong();
                startHeartbeat(heartbeatInterval);
                break;
            case 11:
                logger.debug("Heartbeat ACK received.");
                break;
        }
    }

    private void identify() {
        try {
            String identifyPayload = objectMapper.createObjectNode()
                    .put("op", 2)
                    .set("d", objectMapper.createObjectNode()
                            .put("token", client.getToken())
                            .set("properties", objectMapper.createObjectNode()
                                    .put("os", System.getProperty("os.name"))
                                    .put("browser", "fluxer.java")
                                    .put("device", "fluxer.java")))
                    .toString();
            webSocket.send(identifyPayload);
        } catch (Exception e) {
            logger.error("Failed to identify: ", e);
        }
    }

    private void startHeartbeat(long interval) {
        scheduler.scheduleAtFixedRate(() -> {
            if (connected) {
                logger.debug("Sending heartbeat...");
                webSocket.send("{\"op\": 1}");
            }
        }, interval, interval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        logger.error("Gateway connection failure: {}. Attempting to heal...", t.getMessage());
        this.connected = false;
        reconnect();
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        logger.warn("Gateway connection closing: {} ({})", reason, code);
        this.connected = false;
        if (code != 1000) {
            reconnect();
        }
    }

    private void reconnect() {
        logger.info("Scheduling reconnection in 5 seconds...");
        scheduler.schedule(this::connect, 5, TimeUnit.SECONDS);
    }
}
