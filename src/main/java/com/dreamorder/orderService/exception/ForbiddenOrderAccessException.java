package com.dreamorder.orderService.exception;

public class ForbiddenOrderAccessException extends RuntimeException {
    public ForbiddenOrderAccessException(String message) {
        super(message);
    }
}
