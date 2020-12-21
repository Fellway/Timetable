package com.fryzjer.fryzjer.controller;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;
import com.fryzjer.fryzjer.dto.validator.ReservationValidator;
import com.fryzjer.fryzjer.service.timetable.ITimetableService;
import com.fryzjer.fryzjer.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fryzjer.fryzjer.controller.mapper.TimetableResponseMapper.mapToTimetableResponse;

@RestController
public class TimetableController extends ExceptionHandlerController {

    private ReservationValidator reservationValidator;
    private ITimetableService timetableService;
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public TimetableController(ReservationValidator reservationValidator, final ITimetableService timetableService, final SimpMessageSendingOperations messagingTemplate) {
        this.reservationValidator = reservationValidator;
        this.timetableService = timetableService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/timetable")
    public Timetable getTimetable() {
        return timetableService.getTimetable();
    }

    @PostMapping("/timetable/reserve")
    public void reserve(final Reservation reservation) {
        reservation.setUsername(AuthenticationUtils.getLoggedUser());
        reservationValidator.validateReservation(reservation);
        timetableService.reserve(reservation);
        messagingTemplate.convertAndSend("/topic/timetable", mapToTimetableResponse(timetableService.getTimetable()));
    }

    @PostMapping("/timetable/cancel")
    public void cancel(final Reservation reservation) {
        reservation.setUsername(AuthenticationUtils.getLoggedUser());
        reservationValidator.validateCancelReservation(reservation);
        timetableService.cancelReservation(reservation);
        messagingTemplate.convertAndSend("/topic/timetable", mapToTimetableResponse(timetableService.getTimetable()));
    }
}
