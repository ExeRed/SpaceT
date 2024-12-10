package com.example.SpringT.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private BigDecimal price;

    @Column(unique = true, nullable = false)
    private String returnToken;

    public Ticket() {
        this.returnToken = UUID.randomUUID().toString();
    }

    public Ticket(Long id, Flight flight, Seat seat, BigDecimal price) {
        this();
        this.id = id;
        this.flight = flight;
        this.seat = seat;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getReturnToken() {
        return returnToken;
    }

    public void setReturnToken(String returnToken) {
        this.returnToken = returnToken;
    }
}
