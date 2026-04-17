package com.fluxer.java.examples;

import com.fluxer.java.FluxerBuilder;
import com.fluxer.java.FluxerClient;
import com.fluxer.java.entities.Message;
import com.fluxer.java.events.Subscribe;

public class QuickStart {
    public static void main(String[] args) {
        String token = System.getenv("TOKEN");
        if (token == null || token.isEmpty()) {
            token = "YOUR_TOKEN_HERE"; // Fallback for local development
            System.out.println("Warning: TOKEN environment variable not set. Using fallback token.");
        }

        FluxerClient client = new FluxerBuilder(token)
                .setPrefix("!")
                .build();

        client.registerListener(new QuickStart());
        client.login();
    }

    @Subscribe
    public void onMessage(Message message) {
        if (message.getContent().equalsIgnoreCase("!ping")) {
            message.reply("Pong! 🏓");
        }
    }
}
