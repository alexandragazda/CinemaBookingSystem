package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    //returns all the tickets corresponding to a specified movie
    @Query("select t from Ticket t" +
            " inner join Booking b on b.ID = t.booking.ID" +
            " inner join Showtime s on s.ID=b.showtime.ID " +
            " inner join Movie m on m.ID=s.movie.ID " +
            " where m.ID=?1")
    List<Ticket> findAllByMovieID(Integer movieID);
}
