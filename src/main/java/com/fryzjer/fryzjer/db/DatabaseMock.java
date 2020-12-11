package com.fryzjer.fryzjer.db;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;

public class DatabaseMock {
    private Timetable timetable;

    private static DatabaseMock INSTANCE;

    private DatabaseMock() {
        this.timetable = new Timetable();
    }

    public static DatabaseMock getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseMock();
        }
        return INSTANCE;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void reserve(final Reservation reservation) {
        timetable.setReservation(reservation);
    }

    public void cancelReservation(final Reservation reservation) {
        timetable.removeReservation(reservation);
    }
}
