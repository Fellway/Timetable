package com.fryzjer.fryzjer.service.timetable;

import com.fryzjer.fryzjer.db.DatabaseMock;
import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;
import com.fryzjer.fryzjer.exception.StdBadRequestException;
import org.springframework.stereotype.Service;

import static com.fryzjer.fryzjer.exception.ApiRestError.RESERVATION_ALREADY_EXISTS;
import static com.fryzjer.fryzjer.exception.ApiRestError.RESERVATION_DOESNT_EXISTS;

@Service
public class TimetableService implements ITimetableService {

    private DatabaseMock databaseMock = DatabaseMock.getInstance();

    @Override
    public void reserve(final Reservation reservation) {
        if (!databaseMock.getTimetable().getReservations().contains(reservation)) {
            databaseMock.reserve(reservation);
        } else {
            throw new StdBadRequestException(RESERVATION_ALREADY_EXISTS);
        }
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        if (databaseMock.getTimetable().getReservations().contains(reservation)) {
            databaseMock.cancelReservation(reservation);
        } else {
            throw new StdBadRequestException(RESERVATION_DOESNT_EXISTS);
        }
    }

    @Override
    public Timetable getTimetable() {
        return databaseMock.getTimetable();
    }
}
