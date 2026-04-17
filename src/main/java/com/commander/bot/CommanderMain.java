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
                .setPrefix(".")
                .build();

        client.getAutoMod().setEnabled(true);
        client.registerListener(new CommanderMain());
        client.registerListener(new InteractionListener());
        
        client.login();
        logger.info("🔥 Commander Bot Online!");
    }

    @Subscribe
    public void onMessage(Message msg) {
        if (msg.getContent().equalsIgnoreCase(".commander")) {
            msg.reply("Commander at your service. 🫡");
        }
    }
}
