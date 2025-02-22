package com.app.event_store.infrastructure.persistence.mongo.persistence;

import com.app.event_store.infrastructure.persistence.mongo.constants.CollectionName;
import com.app.event_store.infrastructure.persistence.mongo.repo.EventRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EventRepoImpl implements EventRepo {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EventRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void persistEvent(Document data) {
        try {
            log.info("Document Action {},Status {}, Transaction id {}", data.get("action"), data.get("status"), data.get("transactionId"));
            mongoTemplate.insert(data, CollectionName.COLLECTION_EVENTS);
            log.info("Document saved");
        }catch (Exception e){
            log.error("Exception caught,while saving the data",e);
        }
    }
}
