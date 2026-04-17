package com.fluxer.java.entities.components;

/**
 * Represents a clickable button component. 
 * Even if Fluxer is still catching up, your library is ready for the UI revolution.
 */
public class Button {
    private final String id;
    private final String label;
    private final ButtonStyle style;
    private final boolean disabled;

    public Button(String id, String label, ButtonStyle style, boolean disabled) {
        this.id = id;
        this.label = label;
        this.style = style;
        this.disabled = disabled;
    }

    public static Button primary(String id, String label) {
        return new Button(id, label, ButtonStyle.PRIMARY, false);
    }

    public static Button danger(String id, String label) {
        return new Button(id, label, ButtonStyle.DANGER, false);
    }

    public static Button success(String id, String label) {
        return new Button(id, label, ButtonStyle.SUCCESS, false);
    }

    public enum ButtonStyle {
        PRIMARY(1), SECONDARY(2), SUCCESS(3), DANGER(4), LINK(5);
        private final int value;
        ButtonStyle(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    // Getters for JSON serialization
    public String getId() { return id; }
    public String getLabel() { return label; }
    public ButtonStyle getStyle() { return style; }
    public boolean isDisabled() { return disabled; }
}
