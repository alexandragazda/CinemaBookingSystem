package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.WatchlistMovie;
import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;

import java.util.List;

public interface WatchlistMovieService {
    WatchlistMovie save(WatchlistMovie watchlistMovie);

    WatchlistMovie save(WatchlistMovieDTO watchlistMovieDTO);

    WatchlistMovie findById(Integer id);

    List<WatchlistMovie> findAll();

    List<WatchlistMovie> findAllByWatchlistID(String watchlistID);

    Boolean findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID);

    void delete(String watchlistID, Integer movieID);
}
