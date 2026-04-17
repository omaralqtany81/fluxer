package com.fluxer.java.utils;

import com.fluxer.java.entities.TextChannel;
import com.fluxer.java.entities.components.ActionRow;
import com.fluxer.java.entities.components.Button;
import java.util.List;

public class PaginationManager {
    public static void sendPaginated(TextChannel channel, List<String> items, int itemsPerPage) {
        if (items.isEmpty()) {
            channel.sendMessage("No items to display.");
            return;
        }

        int totalPages = (int) Math.ceil((double) items.size() / itemsPerPage);
        StringBuilder pageContent = new StringBuilder("**Page 1 / " + totalPages + "**\n\n");
        int end = Math.min(itemsPerPage, items.size());
        for (int i = 0; i < end; i++) {
            pageContent.append(items.get(i)).append("\n");
        }

        ActionRow buttons = ActionRow.of(
            Button.primary("page_back", "◀ Back"),
            Button.primary("page_next", "Next ▶")
        );

        channel.sendMessage(pageContent.toString(), buttons);
    }
}
