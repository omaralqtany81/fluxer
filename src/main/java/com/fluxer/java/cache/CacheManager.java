package com.fluxer.java.cache;

import com.fluxer.java.entities.User;
import com.fluxer.java.entities.Role;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * High-performance in-memory storage system. 
 * Reduces API latency by serving frequently accessed entities directly from local memory.
 */
public class CacheManager {
    private final Map<String, User> userCache = new ConcurrentHashMap<>();
    private final Map<String, Role> roleCache = new ConcurrentHashMap<>();

    public void cacheUser(User user) {
        userCache.put(user.getId(), user);
    }

    public User getUser(String id) {
        return userCache.get(id);
    }

    public void cacheRole(Role role) {
        roleCache.put(role.getId(), role);
    }

    public Role getRole(String id) {
        return roleCache.get(id);
    }

    /**
     * Clears expired or unused entries to maintain a low memory footprint.
     */
    public void purge() {
        userCache.clear();
        roleCache.clear();
    }
}
