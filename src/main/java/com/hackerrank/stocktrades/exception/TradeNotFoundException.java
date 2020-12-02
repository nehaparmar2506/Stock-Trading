package com.hackerrank.stocktrades.exception;

/**
 * Custom exception for Trade not found for a certain criteria.
 */
public class TradeNotFoundException extends RuntimeException {

    public TradeNotFoundException(String message) {
        super(message);
    }
}
