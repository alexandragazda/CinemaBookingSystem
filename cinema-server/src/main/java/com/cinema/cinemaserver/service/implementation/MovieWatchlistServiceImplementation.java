package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.MovieWatchlist;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.Watchlist;
import com.cinema.cinemaserver.domain.dtos.MovieWatchlistDTO;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.MovieWatchlistRepository;
import com.cinema.cinemaserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieWatchlistServiceImplementation implements MovieWatchlistService {
    @Autowired
    private MovieWatchlistRepository movieWatchlistRepository;

    @Autowired
    private Validator<MovieWatchlistDTO> validator;

    @Autowired
    private MovieService movieService;

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private UserService userService;

    @Override
    public MovieWatchlist save(MovieWatchlist movieWatchlist) {
        movieWatchlistRepository.save(movieWatchlist);

        return movieWatchlist;
    }

    @Override
    public MovieWatchlist save(MovieWatchlistDTO movieWatchlistDTO) {
        validator.validate(movieWatchlistDTO);

        Movie movie = movieService.findById(movieWatchlistDTO.getMovieID());
        if(movie == null) throw new ServiceException("Cannot find movieID!");

        Watchlist watchlist = watchlistService.findById(movieWatchlistDTO.getWatchlistID());
        if(watchlist==null){ //create a new watchlist for the given user
            User user=userService.findByEmail(movieWatchlistDTO.getWatchlistID());
            if(user==null) throw new ServiceException("Cannot find user email!");
            watchlist = watchlistService.save(new Watchlist(user));
        }

        List<MovieWatchlist> searchList=findAllByWatchlistID(movieWatchlistDTO.getWatchlistID());
        Boolean movieExists=false;
        for (MovieWatchlist mw: searchList
             ) {
            if(mw.getMovie().getID() == movieWatchlistDTO.getMovieID()){
                movieExists=true;
                break;
            }
        }

        if(movieExists) throw new ServiceException("The movie is already in the watchlist!");

        MovieWatchlist movieWatchlist=new MovieWatchlist(movie, watchlist);
        movieWatchlistRepository.save(movieWatchlist);

        System.out.println(movieWatchlist);
        return movieWatchlist;
    }

    @Override
    public MovieWatchlist findById(Integer id) {
        if(movieWatchlistRepository.findById(id).isPresent()) return movieWatchlistRepository.findById(id).get();
        return null;
    }

    @Override
    public List<MovieWatchlist> findAll() {
        return movieWatchlistRepository.findAll();
    }

    @Override
    public List<MovieWatchlist> findAllByWatchlistID(String watchlistID) {
        return movieWatchlistRepository.findAllByWatchlistID(watchlistID);
    }

    @Override
    public Boolean findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID) {
        MovieWatchlist movieWatchlist=movieWatchlistRepository.findAllByWatchlistIDAndMovieID(watchlistID,movieID);
        if(movieWatchlist != null) return true;
        return false;
    }

    @Override
    public void delete(String watchlistID, Integer movieID) {
        MovieWatchlist movieWatchlist=movieWatchlistRepository.findAllByWatchlistIDAndMovieID(watchlistID, movieID);
        if(movieWatchlist == null) throw new ServiceException("Cannot find the specified moviewatchlist entity!");
        movieWatchlistRepository.delete(movieWatchlist);
    }
}
