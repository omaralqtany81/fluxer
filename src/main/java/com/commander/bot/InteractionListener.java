package com.commander.bot;

import com.fluxer.java.entities.interactions.Interaction;
import com.fluxer.java.entities.interactions.SlashCommandInteraction;
import com.fluxer.java.events.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InteractionListener {
    private static final Logger logger = LoggerFactory.getLogger(InteractionListener.class);

    @Subscribe
    public void onInteraction(Interaction interaction) {
        if (interaction instanceof SlashCommandInteraction slash) {
            String command = slash.getCommandName();
            
            // STATUS COMMAND
            if (command.equals("status")) {
                slash.reply("Commander Bot: Integrity 100% 🛡️");
            }

            // THE GIANT /SET COMMAND
            if (command.equals("set")) {
                String key = (String) slash.getOption("key"); // e.g., "leveling_channel"
                String value = (String) slash.getOption("value"); // e.g., "12345678"

                // Save setting locally to database
                interaction.getClient().getDatabase().set("config:" + key, value);
                interaction.getClient().getDatabase().save();

                slash.reply("🛡️ Config Synchronized: Set **" + key + "** to **" + value + "**.");
            }

            // HELP COMMAND
            if (command.equals("help")) {
                com.fluxer.java.utils.EmbedBuilder embed = new com.fluxer.java.utils.EmbedBuilder()
                        .setTitle("🔱 Commander Bot - Command Menu")
                        .setDescription("Welcome, Commander. Here are your available operational directives:")
                        .setColor(java.awt.Color.CYAN)
                        .addField("🛡️ /status", "Checks system integrity.", true)
                        .addField("⚙️ /set <key> <value>", "Updates configuration metadata.", true)
                        .addField("❓ /help", "Displays this interface.", true)
                        .setFooter("Fluxer Bot Engine v1.0.1", null);
                
                slash.reply(embed);
            }
        }
    }
}
