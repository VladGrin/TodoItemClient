package com.todoitem.client.exception;

public class ReceiveException extends Exception {

    private String message;

    public ReceiveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
