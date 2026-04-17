package com.fluxer.java.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final File file;
    private Map<String, Object> data = new HashMap<>();

    public DatabaseManager(String fileName) {
        this.file = new File(fileName);
        load();
    }

    public synchronized void save(String key, Object value) {
        data.put(key, value);
        sync();
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public synchronized void save() {
        sync();
    }

    public Object get(String key) {
        return data.get(key);
    }

    @SuppressWarnings("unchecked")
    private void load() {
        if (!file.exists()) return;
        try {
            data = mapper.readValue(file, Map.class);
        } catch (Exception ignored) {}
    }

    private void sync() {
        try {
            mapper.writeValue(file, data);
        } catch (Exception ignored) {}
    }
}
