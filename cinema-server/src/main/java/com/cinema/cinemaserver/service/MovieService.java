package com.cinema.cinemaserver.service;


import com.cinema.cinemaserver.domain.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface MovieService {
    Movie findById(Integer id);

    Movie findByTitle(String title);

    List<Movie> findAllByDate(LocalDate date);

    List<Movie> findAllTodayByCurrentTime();

    Movie save(Movie movie);

    List<Movie> findAll();

    void delete();
}
