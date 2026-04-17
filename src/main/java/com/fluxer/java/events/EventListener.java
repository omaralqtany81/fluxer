package com.fluxer.java.events;

import com.fluxer.java.entities.Message;

public interface EventListener {
    default void onReady() {}
    default void onMessageReceived(Message message) {}
}
