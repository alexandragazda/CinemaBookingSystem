package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Integer> {

    //returns all the showtimes corresponding to a specified movie
    List<Showtime> findAllByMovieID(Integer movieID);

    //returns all the showtimes from a specific date and corresponding to a specified movie
    List<Showtime> findAllByMovieIDAndDate(Integer movieID, LocalDate date);

    //returns all the showtimes from today, after current time and corresponding to a specified movie
    @Query("select s from Showtime s where s.movie.ID=?1 and s.date=current_date and s.time > ?2 ")
    List<Showtime> findAllTodayByMovieIDAndCurrentTime(Integer movieID, LocalTime time);
}