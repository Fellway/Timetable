package com.fryzjer.fryzjer.service.timetable;

import com.fryzjer.fryzjer.dto.Timetable;

public interface ITimetableService {
    void reserve(final Integer day, final Integer hour, final String username);
    Timetable getTimetable();
}
