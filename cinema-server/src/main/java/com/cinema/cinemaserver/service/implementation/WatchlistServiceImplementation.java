package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Watchlist;
import com.cinema.cinemaserver.repository.UserRepository;
import com.cinema.cinemaserver.repository.WatchlistRepository;
import com.cinema.cinemaserver.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImplementation implements WatchlistService {
    @Autowired
    private WatchlistRepository watchlistRepository;

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public Watchlist save(Watchlist watchlist) {
        watchlistRepository.save(watchlist);

        return watchlist;
    }

    @Override
    public Watchlist findById(String id) {
        if(watchlistRepository.findById(id).isPresent()) return watchlistRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Watchlist> findAll() {
        return watchlistRepository.findAll();
    }

//    @Override
//    public void delete() {
//        userRepository.findById("alexandragazda@yahoo.com").get().setWatchlist(null);
//        watchlistRepository.delete(findById("alexandragazda@yahoo.com"));
//    }
}
