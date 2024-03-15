package com.oasis.taskmanagement.exception;

public class DataValidationException extends RuntimeException {

    private String message;

    public DataValidationException() {
        super();
        this.message = "Validation failed";
    }
    public DataValidationException(String msg) {
        super(msg);
        this.message = msg;
    }
}

