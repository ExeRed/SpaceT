package com.example.SpringT.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(columnDefinition = "BINARY(16)")
    UUID token;
    @Embedded
    Seat ticket;


    public Ticket(UUID token) {
        this.token = token;
    }

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public Ticket() {

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
