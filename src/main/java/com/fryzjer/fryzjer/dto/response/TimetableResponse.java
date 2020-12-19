package com.fryzjer.fryzjer.dto.response;

import com.fryzjer.fryzjer.dto.Reservation;

import java.util.List;

public class TimetableResponse {
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
