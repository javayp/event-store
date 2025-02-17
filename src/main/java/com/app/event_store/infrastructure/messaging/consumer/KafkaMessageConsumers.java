package com.app.event_store.infrastructure.messaging.consumer;


import com.app.event_store.infrastructure.persistence.mongo.service.MongoEventStoreService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaMessageConsumers {

    private MongoEventStoreService mongoEventStoreService;

    @Autowired
    public KafkaMessageConsumers(MongoEventStoreService mongoEventStoreService) {
        this.mongoEventStoreService = mongoEventStoreService;
    }

    /**
     * Batch Consumer
     */
    @KafkaListener(
            topics = "batch-broker-event-topic",
            containerFactory = "batchKafkaListenerContainerFactory"
    )
    public void consumeBatch(List<String> messages) {
        log.info("Batch received: {}", messages);

        // Process each message in the batch
        for (String message : messages) {
            log.info("Processing batch message: {}", message);
        }
    }

    /**
     * Single Message Consumer
     */
    @KafkaListener(
            topics = "single-broker-event-topic",
            containerFactory = "singleKafkaListenerContainerFactory"
    )
    public void consumeSingleMessage(String message) {
        try {
            log.info("Single message received");
            mongoEventStoreService.storeEvents(Document.parse(message));
        }catch (Exception e){
            log.error("Error while saving the data to mongo from kafka events.Message received is {} and the error",message,e);
        }
    }
}
