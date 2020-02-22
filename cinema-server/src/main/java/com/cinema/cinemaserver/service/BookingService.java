package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    void save(Booking booking);

    List<Booking> findAll();

    List<Booking> findAllByShowtimeID(Integer showtimeID);

    Booking save(BookingDTO bookingDTO);

    Booking findByID(Integer ID);

    void delete();
}
