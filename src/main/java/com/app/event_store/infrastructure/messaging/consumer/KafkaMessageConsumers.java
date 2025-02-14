package com.app.event_store.infrastructure.messaging.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaMessageConsumers {


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
        log.info("Single message received: {}", message);

        // Process the single message
    }
}
