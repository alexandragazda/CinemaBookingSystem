package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.dtos.BookingInfoDTO;

import java.util.List;

public interface BookingService {
    void save(Booking booking);

    Booking save(BookingDTO bookingDTO);

    Booking findByID(Integer ID);

    List<Booking> findAll();

    List<Booking> findAllByShowtimeID(Integer showtimeID);

    List<Booking> findAllByUserEmail(String userEmail);

    List<BookingInfoDTO> findFirstExpiredBookings(String userEmail);

    List<BookingInfoDTO> findValidBookings(String userEmail);

    void delete(Integer ID);
}
