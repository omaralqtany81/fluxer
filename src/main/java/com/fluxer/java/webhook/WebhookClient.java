package com.fluxer.java.webhook;

import com.fluxer.java.utils.EmbedBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import java.io.IOException;

/**
 * High-Speed Webhook Engine.
 * Operates independently of the Bot token, perfect for blazing fast logs, 
 * alerts, or interacting with third-party dashboards.
 */
public class WebhookClient {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final String webhookUrl;
    private final OkHttpClient httpClient;

    public WebhookClient(String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.httpClient = new OkHttpClient();
    }

    /**
     * Executes the webhook with text and optional embed.
     */
    public void send(String content, EmbedBuilder embed, String username, String avatarUrl) {
        ObjectNode data = mapper.createObjectNode();
        if (content != null) data.put("content", content);
        if (embed != null) data.set("embed", mapper.valueToTree(embed));
        if (username != null) data.put("username", username);
        if (avatarUrl != null) data.put("avatar_url", avatarUrl);

        RequestBody body = RequestBody.create(data.toString(), JSON);
        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Webhook execution failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.close();
            }
        });
    }
}
