package com.fluxer.java.entities;

/**
 * Represents a role within a guild. 
 * Allows for programmatic permission and hierarchy control.
 */
public class Role {
    private final String id;
    private final String name;
    private final int color;
    private final boolean hoisted;

    public Role(String id, String name, int color, boolean hoisted) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.hoisted = hoisted;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getColor() { return color; }
    public boolean isHoisted() { return hoisted; }
}
