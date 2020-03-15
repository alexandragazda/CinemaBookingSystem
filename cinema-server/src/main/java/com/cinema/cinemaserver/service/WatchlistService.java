package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Watchlist;

import java.util.List;

public interface WatchlistService {
    Watchlist save(Watchlist watchlist);

    Watchlist findById(String id);

    List<Watchlist> findAll();

//    void delete();
}
