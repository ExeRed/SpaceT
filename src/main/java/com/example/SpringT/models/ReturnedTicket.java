package com.example.SpringT.models;

import com.example.SpringT.models.Seat;

public class ReturnedTicket {
    Seat returned_ticket;

    public ReturnedTicket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
