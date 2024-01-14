package com.example.SpringT.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @Embedded
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
