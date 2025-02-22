package com.app.event_store.infrastructure.event;

import com.app.event_store.entities.Transaction;
import com.app.event_store.enums.TransactionStatus;
import com.app.event_store.infrastructure.persistence.mongo.service.MongoEventService;
import com.app.event_store.infrastructure.persistence.sql.service.SqlEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDelegatorImpl implements EventDelegator{

    private final MongoEventService mongoEventService;
    private final SqlEventService sqlEventService;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventDelegatorImpl(MongoEventService mongoEventService, SqlEventService sqlEventService, ObjectMapper objectMapper) {
        this.mongoEventService = mongoEventService;
        this.sqlEventService = sqlEventService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void processEvent(String message) {
        try {
            Transaction transaction = objectMapper.readValue(message, Transaction.class);
            if (!transaction.status().equals(TransactionStatus.EXECUTED)){
                mongoEventService.storeEvents(Document.parse(message));
            }else {
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
