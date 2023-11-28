package com.example.SpringT.models;

import java.io.Serializable;
import java.util.UUID;

import java.util.UUID;

public class Token implements Serializable {

    private UUID token;

    public Token() {}

    public Token(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public static Token fromString(String tokenString) {
        UUID uuid = UUID.fromString(tokenString);
        return new Token(uuid);
    }
}
