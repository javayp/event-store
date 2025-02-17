package com.app.event_store.infrastructure.persistence.mongo.service;

import com.app.event_store.infrastructure.persistence.mongo.constants.CollectionName;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MongoEventStoreService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoEventStoreService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void storeEvents(Document transactionData){
        log.info("Document Action {},Status {}, Transaction id {}",transactionData.get("action"),transactionData.get("status"),transactionData.get("transactionId"));
        mongoTemplate.insert(transactionData, CollectionName.COLLECTION_EVENTS);
        log.info("Document saved");
    }
}
