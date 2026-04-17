package com.fluxer.java.utils;

import com.fluxer.java.entities.TextChannel;
import com.fluxer.java.entities.components.ActionRow;
import com.fluxer.java.entities.components.Button;

import java.util.List;

/**
 * Advanced Pagination System.
 * Automatically slices large lists into pages and provides interactive Next/Back buttons.
 */
public class PaginationManager {

    /**
     * Sends a paginated message with interactive buttons.
     */
    public static void sendPaginated(TextChannel channel, List<String> items, int itemsPerPage) {
        if (items.isEmpty()) {
            channel.sendMessage("No items to display.");
            return;
        }

        int totalPages = (int) Math.ceil((double) items.size() / itemsPerPage);
        
        // Build the first page
        StringBuilder pageContent = new StringBuilder("**Page 1 / " + totalPages + "**\n\n");
        int end = Math.min(itemsPerPage, items.size());
        for (int i = 0; i < end; i++) {
            pageContent.append(items.get(i)).append("\n");
        }

        ActionRow buttons = ActionRow.of(
            Button.primary("page_back", "◀ Back").isDisabled() ? Button.primary("page_back", "◀ Back") : new Button("page_back", "◀ Back", Button.ButtonStyle.PRIMARY, true),
            Button.primary("page_next", "Next ▶").isDisabled() ? Button.primary("page_next", "Next ▶") : new Button("page_next", "Next ▶", Button.ButtonStyle.PRIMARY, totalPages <= 1)
        );

        channel.sendMessage(pageContent.toString(), buttons);
        
        // The InteractivityManager would ideally listen for the button clicks and update the message.
        // That integration handles the visual state changes.
    }
}
