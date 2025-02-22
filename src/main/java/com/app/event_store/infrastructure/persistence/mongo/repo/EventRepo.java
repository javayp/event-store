package com.app.event_store.infrastructure.persistence.mongo.repo;

import org.bson.Document;
import org.springframework.stereotype.Repository;

public interface EventRepo {
    void persistEvent(Document data);
}
