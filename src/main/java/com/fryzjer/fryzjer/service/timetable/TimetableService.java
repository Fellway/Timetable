package com.fryzjer.fryzjer.service.timetable;

import com.fryzjer.fryzjer.db.DatabaseMock;
import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;
import org.springframework.stereotype.Service;

@Service
public class TimetableService implements ITimetableService {

    private DatabaseMock databaseMock = DatabaseMock.getInstance();

    @Override
    public void reserve(final Integer day, final Integer hour, final String username) {
        Reservation reservation = new Reservation(day, hour, username);
        if(databaseMock.getTimetable().getReservations().contains(reservation)) {
            databaseMock.cancelReservation(reservation);
        } else {
            databaseMock.reserve(reservation);
        }
    }

    @Override
    public Timetable getTimetable() {
        return databaseMock.getTimetable();
    }
}