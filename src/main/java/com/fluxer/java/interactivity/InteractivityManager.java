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

public class InteractivityManager {
    private final List<WaitingRequest<Message>> messageRequests = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
