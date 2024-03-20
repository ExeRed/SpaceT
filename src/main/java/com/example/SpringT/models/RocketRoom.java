package com.example.SpringT.models;

import java.util.ArrayList;
import java.util.List;

public class RocketRoom {
    int total_rows;
    int total_columns;

    private List<Seat> available_seats;

    public static List<Seat> seats = new ArrayList<>();

    public RocketRoom() {}

    public RocketRoom(int total_rows, int total_columns, List<Seat> available_seats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    public static RocketRoom getAllseat(int rows, int columns){
        for (int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                if (i < 5) {
                    seats.add(new Seat(i, j, 10));
                } else {
                    seats.add(new Seat(i, j, 8));
                }
            }
        }
        return new RocketRoom(rows, columns, seats);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }
}
