package com.todoitem.client.exception;

public class NoContentException extends Exception {

    private String message;

    public NoContentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
