package com.app.event_store.entities;

import com.app.event_store.enums.TransactionStatus;

import java.time.LocalDateTime;


public record Transaction (
     String transactionId,
     String userId,
     String assetType,
     String action,
     int quantity,
     double price,
     TransactionStatus status,
     LocalDateTime createdAt){}
