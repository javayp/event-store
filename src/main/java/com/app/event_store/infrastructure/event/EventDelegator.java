package com.app.event_store.infrastructure.event;

public interface EventDelegator {
    void processEvent(String message);
}
