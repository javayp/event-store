package com.app.event_store.infrastructure.persistence.mongo.service;

import com.app.event_store.infrastructure.persistence.mongo.repo.EventRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MongoEventService {

    private final EventRepo eventRepo;

    @Autowired
    public MongoEventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public void storeEvents(Document transactionData){
        eventRepo.persistEvent(transactionData);
    }
}
