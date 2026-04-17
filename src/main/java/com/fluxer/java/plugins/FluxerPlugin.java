package com.fluxer.java.plugins;

import com.fluxer.java.FluxerClient;

public interface FluxerPlugin {
    void onEnable(FluxerClient client);
    void onDisable(FluxerClient client);
    String getName();
    String getVersion();
}
