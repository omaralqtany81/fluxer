package com.fluxer.java.events;

import java.lang.reflect.Method;
import java.util.List;

public class EventBus {
    public void post(Object event, List<Object> listeners) {
        for (Object listener : listeners) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    if (method.getParameterCount() == 1 && method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                        try {
                            method.setAccessible(true);
                            method.invoke(listener, event);
                        } catch (Exception ignored) {}
                    }
                }
            }
        }
    }
}
