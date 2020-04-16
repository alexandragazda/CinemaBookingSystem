package com.cinema.cinemaserver.service;


import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.dtos.ShowtimeDTOS;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {
    Showtime findById(Integer id);

    Showtime save(Showtime showtime);

    List<Showtime> findAll();

    List<Showtime> findAllByMovieId(Integer movieId);

    List<Showtime> findAllByMovieIdAndDate(Integer movieId, LocalDate date);

    List<Showtime> findAllTodayByMovieIdAndCurrentTime(Integer movieId);

    List<Showtime> findAllByMovieIdAndDateAvailable(Integer movieId, LocalDate date);

    ShowtimeDTOS findShowtimeDTOSByMovieIdAndDate(Integer movieId, LocalDate date);
}
