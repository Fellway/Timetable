package com.fryzjer.fryzjer.dto;

import java.util.Objects;

public class Reservation {
    private Integer day;
    private Integer hour;
    private String username;

    public Reservation() {
    }

    public Reservation(Integer day, Integer hour, String username) {
        this.username = username;
        this.day = day;
        this.hour = hour;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;

        Reservation that = (Reservation) o;

        if (!Objects.equals(day, that.day)) return false;
        if (!Objects.equals(hour, that.hour)) return false;
        return Objects.equals(username, that.username);
    }
}
