package com.example.SpringT.securities;

public class SecurityException2 extends RuntimeException {

    private static final long serialVersionUID = -7806029002430564887L;

    private String message;

    public SecurityException2() {
    }

    public SecurityException2(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
