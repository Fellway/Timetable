package com.fryzjer.fryzjer.service.timetable;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;

public interface ITimetableService {
    void reserve(final Reservation reservation);
    void cancelReservation(final Reservation reservation);
    Timetable getTimetable();
}
