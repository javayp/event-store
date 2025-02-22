package com.app.event_store.enums;

public enum TransactionStatus {
    INITIATED,
    VALIDATED,  // The transaction has passed all validations (e.g., balance checks, asset availability).
    EXECUTED,   // The transaction has been successfully completed (e.g., assets bought/sold).
    FAILED,     // The transaction failed due to an issue (e.g., insufficient balance, asset unavailability).
    CANCELLED   // The transaction was cancelled by the user or the system.
}
