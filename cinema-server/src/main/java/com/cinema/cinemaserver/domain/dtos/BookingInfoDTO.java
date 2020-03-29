package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.enums.Technology;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingInfoDTO {
    private Integer bookingID;
    private Integer nrChildTickets;
    private Integer nrStudentTickets;
    private Integer nrAdultTickets;
    private Integer nrRetiredTickets;
    private Double totalPrice;
    private String seats;
    private String movieTitle;
    private Technology movieTechnology;
    private LocalDate showtimeDate;
    private LocalTime showtimeTime;
    private Integer screenID;

    public BookingInfoDTO() { }

    public BookingInfoDTO(Integer bookingID, Integer nrChildTickets, Integer nrStudentTickets, Integer nrAdultTickets, Integer nrRetiredTickets, Double totalPrice, String seats, String movieTitle, Technology movieTechnology, LocalDate showtimeDate, LocalTime showtimeTime, Integer screenID) {
        this.bookingID = bookingID;
        this.nrChildTickets = nrChildTickets;
        this.nrStudentTickets = nrStudentTickets;
        this.nrAdultTickets = nrAdultTickets;
        this.nrRetiredTickets = nrRetiredTickets;
        this.totalPrice = totalPrice;
        this.seats = seats;
        this.movieTitle = movieTitle;
        this.movieTechnology = movieTechnology;
        this.showtimeDate = showtimeDate;
        this.showtimeTime = showtimeTime;
        this.screenID = screenID;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getNrChildTickets() {
        return nrChildTickets;
    }

    public void setNrChildTickets(Integer nrChildTickets) {
        this.nrChildTickets = nrChildTickets;
    }

    public Integer getNrStudentTickets() {
        return nrStudentTickets;
    }

    public void setNrStudentTickets(Integer nrStudentTickets) {
        this.nrStudentTickets = nrStudentTickets;
    }

    public Integer getNrAdultTickets() {
        return nrAdultTickets;
    }

    public void setNrAdultTickets(Integer nrAdultTickets) {
        this.nrAdultTickets = nrAdultTickets;
    }

    public Integer getNrRetiredTickets() {
        return nrRetiredTickets;
    }

    public void setNrRetiredTickets(Integer nrRetiredTickets) {
        this.nrRetiredTickets = nrRetiredTickets;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Technology getMovieTechnology() {
        return movieTechnology;
    }

    public void setMovieTechnology(Technology movieTechnology) {
        this.movieTechnology = movieTechnology;
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

    public Integer getScreenID() {
        return screenID;
    }

    public void setScreenID(Integer screenID) {
        this.screenID = screenID;
    }

    @Override
    public String toString() {
        return "BookingInfoDTO:" +
                "bookingID=" + bookingID + " | " +
                "nrChildTickets=" + nrChildTickets + " | " +
                "nrStudentTickets=" + nrStudentTickets + " | " +
                "nrAdultTickets=" + nrAdultTickets + " | " +
                "nrRetiredTickets=" + nrRetiredTickets + " | " +
                "totalPrice=" + totalPrice + " | " +
                "seats='" + seats + " | "  +
                "movieTitle='" + movieTitle + " | " +
                "movieTechnology=" + movieTechnology + " | " +
                "showtimeDate=" + showtimeDate + " | " +
                "showtimeTime=" + showtimeTime + " | " +
                "screenID=" + screenID;
    }
}
