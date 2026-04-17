package com.fluxer.java.entities.components;

/**
 * Supported component types in Fluxer. 
 * Designed for future-proof integration.
 */
public enum ComponentType {
    ACTION_ROW(1),
    BUTTON(2),
    SELECT_MENU(3);

    private final int id;
    ComponentType(int id) { this.id = id; }
    public int getId() { return id; }
}
