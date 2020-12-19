package com.fryzjer.fryzjer.controller;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;
import com.fryzjer.fryzjer.dto.validator.ReservationValidator;
import com.fryzjer.fryzjer.service.timetable.ITimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/reserve")
    public void reserve(Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            reservation.setUsername(authentication.getName());
            reservationValidator.validateReservation(reservation);
            timetableService.reserve(reservation);
        }
        messagingTemplate.convertAndSend("/topic/timetable", mapToTimetableResponse(timetableService.getTimetable()));
    }
}
