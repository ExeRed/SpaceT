package com.example.SpringT.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

import java.util.UUID;

@Embeddable
public class Token implements Serializable {

    private String token;

    public Token() {}

    public Token(UUID token) {
        this.token = token.toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
