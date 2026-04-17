package com.fluxer.java.entities.components;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for components like Buttons. 
 * Allows organizing your bot's UI into distinct rows.
 */
public class ActionRow {
    private final List<Button> buttons = new ArrayList<>();

    public static ActionRow of(Button... buttons) {
        ActionRow row = new ActionRow();
        for (Button b : buttons) {
            row.buttons.add(b);
        }
        return row;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public ComponentType getType() {
        return ComponentType.ACTION_ROW;
    }
}
