package com.oasis.taskmanagement.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException() {
        super();
        this.message = "Validation failed";
    }
    public ResourceNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
