package com.fryzjer.fryzjer.dto.validator;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.exception.StdBadRequestException;
import com.fryzjer.fryzjer.service.timetable.ITimetableService;
import com.fryzjer.fryzjer.service.timetable.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.fryzjer.fryzjer.exception.ApiRestError.ONE_RESERVATION_PER_DAY;
import static com.fryzjer.fryzjer.exception.ApiRestError.RESERVATION_IS_NOT_ASSIGNED_TO_THIS_USER;

@Component
public class ReservationValidator {

    private ITimetableService timetableService;

    @Autowired
    public ReservationValidator(final TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    public void validateReservation(final Reservation reservation) {
        if (reservationIsOneAtMostOnePerDay(reservation)) {
            throw new StdBadRequestException(ONE_RESERVATION_PER_DAY);
        }
    }

    public void validateCancelReservation(final Reservation reservation) {
        if (reservationIsNotAssignedToUser(reservation)) {
            throw new StdBadRequestException(RESERVATION_IS_NOT_ASSIGNED_TO_THIS_USER);
        }
    }

    private boolean reservationIsNotAssignedToUser(final Reservation reservation) {
        return timetableService.getTimetable().getReservations()
                .stream()
                .filter(r -> r.getHour().equals(reservation.getHour()) && r.getDay().equals(reservation.getDay()))
                .anyMatch(r -> !r.getUsername().equals(reservation.getUsername()));
    }

    private boolean reservationIsOneAtMostOnePerDay(final Reservation reservation) {
        return timetableService.getTimetable().getReservations()
                .stream()
                .anyMatch(r -> r.getDay().equals(reservation.getDay()) && r.getUsername().equals(reservation.getUsername()));
    }
}
