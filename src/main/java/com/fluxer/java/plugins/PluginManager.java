package com.fluxer.java.plugins;

import com.fluxer.java.FluxerClient;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {
    private final List<FluxerPlugin> loadedPlugins = new ArrayList<>();
    private final FluxerClient client;

    public PluginManager(FluxerClient client) {
        this.client = client;
    }

    public void registerPlugin(FluxerPlugin plugin) {
        plugin.onEnable(client);
        loadedPlugins.add(plugin);
    }

    public void disableAll() {
        loadedPlugins.forEach(p -> p.onDisable(client));
        loadedPlugins.clear();
    }
}
