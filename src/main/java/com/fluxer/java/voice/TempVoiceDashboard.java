package com.fluxer.java.voice;

import com.fluxer.java.entities.components.ActionRow;
import com.fluxer.java.entities.components.Button;
import com.fluxer.java.utils.EmbedBuilder;
import com.fluxer.java.entities.TextChannel;
import java.awt.Color;

/**
 * Utility to build the high-end Room Control dashboard as seen in professional setups.
 */
public class TempVoiceDashboard {

    public static void send(TextChannel channel, String ownerMention) {
        EmbedBuilder embed = new EmbedBuilder()
            .setTitle("Room Control")
            .setDescription("Welcome to your private room, " + ownerMention + "!\nUse the buttons below to manage your space.")
            .setColor(new Color(114, 137, 218));

        // Row 1: Basic Stats
        ActionRow row1 = ActionRow.of(
            Button.primary("vc_name", "Change Name"),
            Button.primary("vc_limit", "Change Limit"),
            Button.primary("vc_bitrate", "Change Bitrate")
        );

        // Row 2: Member Management
        ActionRow row2 = ActionRow.of(
            Button.success("vc_add", "Add Member"),
            Button.danger("vc_kick", "Kick Member"),
            Button.primary("vc_region", "Change Region")
        );

        // Row 3: Permissions
        ActionRow row3 = ActionRow.of(
            Button.primary("vc_lock", "Join Permission"),
            Button.primary("vc_mute", "Speak Permission"),
            Button.danger("vc_delete", "Delete Channel")
        );

        channel.sendMessage("", row1, row2, row3);
    }
}
