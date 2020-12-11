package com.fryzjer.fryzjer.dto;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private List<Reservation> reservations;

    public Timetable() {
        this.reservations = new ArrayList<>();
    }

    public Timetable(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setReservation(final Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(final Reservation reservation) {
        reservations.remove(reservation);
    }
}
