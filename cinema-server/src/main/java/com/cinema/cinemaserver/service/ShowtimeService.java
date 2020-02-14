package com.cinema.cinemaserver.service;


import com.cinema.cinemaserver.domain.Showtime;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {
    Showtime findById(Integer id);

    Showtime save(Showtime showtime);

    List<Showtime> findAll();

    List<Showtime> findAllByMovieId(Integer movieId);

    List<Showtime> findAllByMovieIdAndDate(Integer movieId, LocalDate date);

    List<Showtime> findAllTodayByMovieIdAndCurrentTIme(Integer movieId);

    void delete();
}
