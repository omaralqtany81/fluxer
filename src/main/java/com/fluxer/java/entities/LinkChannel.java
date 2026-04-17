package com.fluxer.java.entities;

import com.fluxer.java.FluxerClient;

/**
 * Represents a specialized Fluxer channel that redirects users to a URL.
 */
public class LinkChannel {
    private final String id;
    private final String name;
    private final String url;
    private final FluxerClient client;

    public LinkChannel(String id, String name, String url, FluxerClient client) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void editUrl(String newUrl) {
        // Fluxer specific endpoint would go here
    }
}
