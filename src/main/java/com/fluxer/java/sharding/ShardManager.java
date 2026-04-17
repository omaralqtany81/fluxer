package com.fluxer.java.sharding;

import com.fluxer.java.FluxerClient;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates multiple Shard instances to distribute load for large-scale applications.
 */
public class ShardManager {
    private final List<FluxerClient> shards = new ArrayList<>();
    private final String token;
    private final int totalShards;

    public ShardManager(String token, int totalShards) {
        this.token = token;
        this.totalShards = totalShards;
    }

    /**
     * Spawns and initializes all shards in parallel.
     */
    public void start() {
        for (int i = 0; i < totalShards; i++) {
            // In a real implementation, each client would receive its shard ID
            // FluxerClient shard = new FluxerBuilder(token).setShard(i, totalShards).build();
            // shards.add(shard);
            // shard.login();
        }
    }

    public List<FluxerClient> getShards() {
        return shards;
    }
}
