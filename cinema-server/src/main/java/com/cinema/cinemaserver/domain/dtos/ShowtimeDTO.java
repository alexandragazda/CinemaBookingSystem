package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.enums.Technology;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowtimeDTO {
    private Integer showtimeID;
    private LocalDate showtimeDate;
    private LocalTime showtimeTime;
    private Technology showtimeTechnology;
    private Integer screenID;

    public ShowtimeDTO() { }

    public ShowtimeDTO(Integer showtimeID, LocalDate showtimeDate, LocalTime showtimeTime, Technology showtimeTechnology, Integer screenID) {
        this.showtimeID = showtimeID;
        this.showtimeDate = showtimeDate;
        this.showtimeTime = showtimeTime;
        this.showtimeTechnology = showtimeTechnology;
        this.screenID = screenID;
    }

    public Integer getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(Integer showtimeID) {
        this.showtimeID = showtimeID;
    }

    public LocalDate getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(LocalDate showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public LocalTime getShowtimeTime() {
        return showtimeTime;
    }

    public void setShowtimeTime(LocalTime showtimeTime) {
        this.showtimeTime = showtimeTime;
    }

    public Technology getShowtimeTechnology() {
        return showtimeTechnology;
    }

    public void setShowtimeTechnology(Technology showtimeTechnology) {
        this.showtimeTechnology = showtimeTechnology;
    }

    public Integer getScreenID() {
        return screenID;
    }

    public void setScreenID(Integer screenID) {
        this.screenID = screenID;
    }

    @Override
    public String toString() {
        return "ShowtimeDTO:" +
                "showtimeID=" + showtimeID + " | " +
                "showtimeDate=" + showtimeDate + " | " +
                "showtimeTime=" + showtimeTime + " | " +
                "showtimeTechnology=" + showtimeTechnology + " | " +
                "screenID=" + screenID;
    }
}
