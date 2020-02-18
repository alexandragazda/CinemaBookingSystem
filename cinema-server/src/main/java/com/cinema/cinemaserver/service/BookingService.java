package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    void save(Booking booking);

    List<Booking> findAll();

    List<Booking> findAllByScreenIDAndDateAndTime(Integer screenID, LocalDate date, LocalTime time);

    List<List<Integer>> stateOfSeats(Integer screenID, LocalDate date, LocalTime time);

    Booking save(BookingDTO bookingDTO);

    Booking findByID(Integer ID);
}
