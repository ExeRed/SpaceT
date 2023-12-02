package com.example.SpringT.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARBINARY(16)")
    private byte[] token;

    @Embedded
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(UUID token, Seat ticket) {
        this.token = token != null ? token.toString().getBytes() : null;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public UUID getToken() {
        return token != null ? UUID.nameUUIDFromBytes(token) : null;
    }

    public void setToken(UUID token) {
        this.token = token != null ? token.toString().getBytes() : null;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
