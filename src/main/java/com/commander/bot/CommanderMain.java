package com.commander.bot;

import com.fluxer.java.FluxerBuilder;
import com.fluxer.java.FluxerClient;
import com.fluxer.java.entities.Message;
import com.fluxer.java.events.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommanderMain {
    private static final Logger logger = LoggerFactory.getLogger(CommanderMain.class);

    public static void main(String[] args) {
        String token = System.getenv("FLUXER_TOKEN");
        if (token == null) token = "YOUR_TOKEN_HERE";

        FluxerClient client = new FluxerBuilder(token)
                .setPrefix("!")
                .build();

        client.getAutoMod().setEnabled(true);
        client.registerListener(new CommanderMain());
        client.registerListener(new InteractionListener());
        
        // Register Text Commands
        client.getCommandHandler().register("status", ctx -> {
            ctx.getMessage().reply("Commander Bot: Integrity 100% 🛡️ (Text Mode)");
        });

        client.getCommandHandler().register("set", ctx -> {
            if (ctx.getArgs().length < 2) {
                ctx.getMessage().reply("❌ Usage: !set <key> <value>");
                return;
            }
            String key = ctx.getArgs()[0];
            String value = ctx.getArgs()[1];
            client.getDatabase().set("config:" + key, value);
            client.getDatabase().save();
            ctx.getMessage().reply("🛡️ Config Synchronized (Text): **" + key + "** = **" + value + "**.");
        });

        client.getCommandHandler().register("help", ctx -> {
            com.fluxer.java.utils.EmbedBuilder embed = new com.fluxer.java.utils.EmbedBuilder()
                    .setTitle("🔱 Commander Bot - Command Menu")
                    .setDescription("Welcome, Commander. Here are your available operational directives (Text Mode):")
                    .setColor(java.awt.Color.CYAN)
                    .addField("🛡️ !status", "Checks system integrity.", true)
                    .addField("⚙️ !set <key> <value>", "Updates configuration metadata.", true)
                    .addField("❓ !help", "Displays this interface.", true)
                    .setFooter("Fluxer Bot Engine v1.0.1", null);

            ctx.getMessage().getChannel().sendMessage(embed);
        });

        client.login();
        logger.info("🔥 Commander Bot Online & Listening for ! commands");
    }

    @Subscribe
    public void onMessage(Message msg) {
        if (msg.getContent().equalsIgnoreCase(".commander")) {
            msg.reply("Commander at your service. 🫡");
        }
    }
}
