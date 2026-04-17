package com.fluxer.java.interactivity;

import com.fluxer.java.entities.Message;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A highly advanced Interactivity Engine.
 * Allows developers to "wait" for specific events (like a user replying or clicking a button)
 * without blocking the main thread, operating entirely asynchronously.
 */
public class InteractivityManager {
    private final List<WaitingRequest<Message>> messageRequests = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Waits for a message that matches the given condition.
     * @param condition The condition the message must meet (e.g., from a specific user).
     * @param timeout The maximum time to wait.
     * @param unit The time unit.
     * @return A CompletableFuture containing the matching Message.
     */
    public CompletableFuture<Message> waitForMessage(Predicate<Message> condition, long timeout, TimeUnit unit) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        WaitingRequest<Message> request = new WaitingRequest<>(condition, future);
        messageRequests.add(request);

        scheduler.schedule(() -> {
            if (messageRequests.remove(request) && !future.isDone()) {
                future.completeExceptionally(new TimeoutException("Timed out waiting for message."));
            }
        }, timeout, unit);

        return future;
    }

    /**
     * Hook called by the GatewayManager when a new message arrives.
     */
    public void dispatchMessage(Message message) {
        for (WaitingRequest<Message> request : messageRequests) {
            if (request.condition.test(message)) {
                messageRequests.remove(request);
                request.future.complete(message);
            }
        }
    }

    private static class WaitingRequest<T> {
        final Predicate<T> condition;
        final CompletableFuture<T> future;

        WaitingRequest(Predicate<T> condition, CompletableFuture<T> future) {
            this.condition = condition;
            this.future = future;
        }
    }
}
