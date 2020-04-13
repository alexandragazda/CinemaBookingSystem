package com.cinema.cinemaserver.service;


import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.dtos.MovieDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface MovieService {
    Movie findById(Integer id);

    Movie findByTitle(String title);

    List<Movie> findAllByDate(LocalDate date);

    List<Movie> findAllTodayByCurrentTime();

    List<MovieDTO> findAllByWatchlistID(String watchlistID);

    List<Movie> findAllByReleaseDate();

    List<Movie> findAllByReleaseMonth(String month);

    List<MovieDTO> comingSoon(Integer month);

    Movie save(Movie movie);

    List<Movie> findAll();

    List<Movie> findAllByEndDate();

    List<MovieDTO> findAllAvailable();

    List<MovieDTO> findTop();

    List<MovieDTO> getMovieDTOList(List<Movie> movies);

    void delete();
}
