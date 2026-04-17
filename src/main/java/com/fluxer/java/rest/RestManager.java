package com.fluxer.java.rest;

import com.fluxer.java.FluxerClient;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class RestManager {
    private static final Logger logger = LoggerFactory.getLogger(RestManager.class);
    private static final String API_BASE_URL = "https://api.fluxer.app/v1";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final FluxerClient client;
    private final OkHttpClient httpClient;

    public RestManager(FluxerClient client) {
        this.client = client;
        this.httpClient = new OkHttpClient();
    }

    public CompletableFuture<Response> post(String endpoint, String json) {
        CompletableFuture<Response> future = new CompletableFuture<>();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_BASE_URL + endpoint)
                .header("Authorization", "Bot " + client.getToken())
                .post(body)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("REST Request failed: ", e);
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    logger.error("REST Request failed with code {}: {}", response.code(), response.body().string());
                } else {
                    logger.debug("REST Request successful: {}", response.code());
                }
                future.complete(response);
            }
        });
        return future;
    }

    public CompletableFuture<Response> get(String endpoint) {
        CompletableFuture<Response> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(API_BASE_URL + endpoint)
                .header("Authorization", "Bot " + client.getToken())
                .get()
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("REST Request failed: ", e);
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                future.complete(response);
            }
        });
        return future;
    }
}
