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

    //returns all the bookings made for given screen, date and time
    @Query("select b from Booking b " +
            "inner join Showtime s on s.ID = b.showtime.ID " +
            "inner join Screen sc on sc.ID = s.screen.ID " +
            "where sc.ID=?1 and s.date=?2 and s.time=?3")
    List<Booking> findAllByScreenIDAndDateAndTime(Integer screenID, LocalDate date, LocalTime time);
}
