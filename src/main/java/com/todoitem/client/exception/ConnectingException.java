package com.todoitem.client.exception;

public class ConnectingException extends Exception {
    private String message;

    public ConnectingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
