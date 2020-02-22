package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b inner join Showtime s on s.ID = b.showtime.ID where s.ID = ?1")
    List<Booking> findAllByShowtimeID(Integer showtimeID);
}
