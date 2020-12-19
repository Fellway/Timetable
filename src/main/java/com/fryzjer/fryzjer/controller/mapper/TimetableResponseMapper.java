package com.fryzjer.fryzjer.controller.mapper;

import com.fryzjer.fryzjer.dto.Timetable;
import com.fryzjer.fryzjer.dto.response.TimetableResponse;

public class TimetableResponseMapper {
    private TimetableResponseMapper(){}

    public static TimetableResponse mapToTimetableResponse(final Timetable timetable) {
        final TimetableResponse timetableResponse = new TimetableResponse();
        timetableResponse.setReservations(timetable.getReservations());
        return timetableResponse;
    }
}
