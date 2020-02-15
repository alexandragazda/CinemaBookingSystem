package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Integer> {

    @Query("select s from Showtime s where s.movie.ID=?1")
    List<Showtime> findAllByMovieId(Integer movieId);

    @Query("select s from Showtime s where s.movie.ID=?1 and s.date=?2")
    List<Showtime> findAllByMovieIdAndDate(Integer movieId, LocalDate date);

    @Query("select s from Showtime s where s.movie.ID=?1 and s.date='2020-02-14' and s.time > current_time ") // !!!!! today
    List<Showtime> findAllTodayByMovieIdAndCurrentTime(Integer movieId);
}