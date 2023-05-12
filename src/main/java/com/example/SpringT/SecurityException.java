package com.example.SpringT;

public class SecurityException extends RuntimeException {

    private static final long serialVersionUID = -7806029002430564887L;

    private String message;

    public SecurityException() {
    }

    public SecurityException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}