package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.dtos.BookingInfoDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    void save(Booking booking);

    List<Booking> findAll();

    List<Booking> findAllByShowtimeID(Integer showtimeID);

    List<Booking> findAllByUserEmail(String userEmail);

    Booking save(BookingDTO bookingDTO);

    Booking findByID(Integer ID);

    List<BookingInfoDTO> findFirstExpiredBookings(String userEmail);

    void delete();
}
