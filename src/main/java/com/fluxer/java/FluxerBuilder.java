package com.fluxer.java;

import java.util.ArrayList;
import java.util.List;

public class FluxerBuilder {
    private String token;
    private String prefix = "!";
    private final List<Object> listeners = new ArrayList<>();

    public FluxerBuilder(String token) {
        this.token = token;
    }

    public FluxerBuilder setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public FluxerBuilder addEventListeners(Object... listeners) {
        for (Object listener : listeners) {
            this.listeners.add(listener);
        }
        return this;
    }

    public FluxerClient build() {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty!");
        }
        return new FluxerClient(token, listeners, prefix);
    }
}
