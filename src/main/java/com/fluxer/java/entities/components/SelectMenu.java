package com.fluxer.java.entities.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a select menu component in a message.
 */
public class SelectMenu {
    private final ComponentType type = ComponentType.SELECT_MENU;
    private final String customId;
    private final String placeholder;
    private final List<Option> options = new ArrayList<>();

    public SelectMenu(String customId, String placeholder) {
        this.customId = customId;
        this.placeholder = placeholder;
    }

    public void addOption(String label, String value, String description) {
        this.options.add(new Option(label, value, description));
    }

    public String getCustomId() { return customId; }
    public String getPlaceholder() { return placeholder; }
    public List<Option> getOptions() { return options; }

    public static class Option {
        private final String label;
        private final String value;
        private final String description;

        public Option(String label, String value, String description) {
            this.label = label;
            this.value = value;
            this.description = description;
        }

        public String getLabel() { return label; }
        public String getValue() { return value; }
        public String getDescription() { return description; }
    }
}
