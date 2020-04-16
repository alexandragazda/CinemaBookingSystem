package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.WatchlistMovie;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.Watchlist;
import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.WatchlistMovieRepository;
import com.cinema.cinemaserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistMovieServiceImplementation implements WatchlistMovieService {
    @Autowired
    private WatchlistMovieRepository watchlistMovieRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private UserService userService;

    @Autowired
    private Validator<WatchlistMovieDTO> validator;

    @Override
    public WatchlistMovie save(WatchlistMovie watchlistMovie) {
        watchlistMovieRepository.save(watchlistMovie);

        return watchlistMovie;
    }

    @Override
    public WatchlistMovie save(WatchlistMovieDTO watchlistMovieDTO) {
        validator.validate(watchlistMovieDTO);

        Movie movie = movieService.findById(watchlistMovieDTO.getMovieID());
        if(movie == null) throw new ServiceException("Cannot find movieID!");

        Watchlist watchlist = watchlistService.findById(watchlistMovieDTO.getWatchlistID());
        if(watchlist==null){ //create a new watchlist for the given user
            User user=userService.findByEmail(watchlistMovieDTO.getWatchlistID());
            if(user==null) throw new ServiceException("Cannot find user email!");
            watchlist = watchlistService.save(new Watchlist(user));
        }

        List<WatchlistMovie> searchList=findAllByWatchlistID(watchlistMovieDTO.getWatchlistID());
        boolean movieExists=false;
        for (WatchlistMovie mw: searchList
             ) {
            if(mw.getMovie().getID() == watchlistMovieDTO.getMovieID()){
                movieExists=true;
                break;
            }
        }

        if(movieExists) throw new ServiceException("The movie is already in the watchlist!");

        WatchlistMovie watchlistMovie =new WatchlistMovie(watchlist, movie);
        watchlistMovieRepository.save(watchlistMovie);

        return watchlistMovie;
    }

    @Override
    public WatchlistMovie findById(Integer id) {
        if(watchlistMovieRepository.findById(id).isPresent()) return watchlistMovieRepository.findById(id).get();
        return null;
    }

    @Override
    public List<WatchlistMovie> findAll() {
        return watchlistMovieRepository.findAll();
    }

    @Override
    public List<WatchlistMovie> findAllByWatchlistID(String watchlistID) {
        return watchlistMovieRepository.findAllByWatchlistID(watchlistID);
    }

    @Override
    public Boolean findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID) {
        WatchlistMovie watchlistMovie = watchlistMovieRepository.findByWatchlistIDAndMovieID(watchlistID,movieID);
        if(watchlistMovie != null) return true;
        return false;
    }

    @Override
    public void delete(String watchlistID, Integer movieID) {
        WatchlistMovie watchlistMovie = watchlistMovieRepository.findByWatchlistIDAndMovieID(watchlistID, movieID);
        if(watchlistMovie == null) throw new ServiceException("Cannot find the specified watchlistmovie entity!");
        watchlistMovieRepository.delete(watchlistMovie);
    }
}
