package com.example.SpringT.models;


import javax.persistence.Embeddable;

@Embeddable
public class Seat {
    int seat_row;
    int seat_column;
    int price;

    public Seat() {}

    public Seat(int seat_row, int seat_column, int price) {
        this.seat_row = seat_row;
        this.seat_column = seat_column;
        this.price = price;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int seat_row) {
        this.seat_row = seat_row;
    }

    public int getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(int seat_column) {
        this.seat_column = seat_column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
