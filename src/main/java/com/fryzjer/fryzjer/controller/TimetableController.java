package com.fryzjer.fryzjer.controller;

import com.fryzjer.fryzjer.dto.Reservation;
import com.fryzjer.fryzjer.dto.Timetable;
import com.fryzjer.fryzjer.service.timetable.ITimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimetableController {

    private ITimetableService timetableService;

    @Autowired
    public TimetableController(final ITimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/timetable")
    public Timetable getTimetable() {
        return timetableService.getTimetable();
    }

    @MessageMapping("/reserve")
    @SendTo("/topic/timetable")
    public Timetable reserve(final Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            timetableService.reserve(reservation.getDay(), reservation.getHour(), currentUserName);
        }
        return timetableService.getTimetable();
    }
}
