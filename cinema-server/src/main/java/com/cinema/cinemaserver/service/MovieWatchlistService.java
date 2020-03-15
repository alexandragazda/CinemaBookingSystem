package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.MovieWatchlist;
import com.cinema.cinemaserver.domain.dtos.MovieWatchlistDTO;

import java.util.List;

public interface MovieWatchlistService {
    MovieWatchlist save(MovieWatchlist movieWatchlist);

    MovieWatchlist save(MovieWatchlistDTO movieWatchlistDTO);

    MovieWatchlist findById(Integer id);

    List<MovieWatchlist> findAll();

    List<MovieWatchlist> findAllByWatchlistID(String watchlistID);

    Boolean findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID);

    void delete(String watchlistID, Integer movieID);
}
