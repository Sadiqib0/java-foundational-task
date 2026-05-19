package com.dreamorder.orderService.exception;

public class CancellationWindowExpiredException extends RuntimeException {
    public CancellationWindowExpiredException(String message) {
        super(message);
    }
}
