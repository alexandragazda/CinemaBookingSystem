package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.WatchlistMovie;
import com.cinema.cinemaserver.domain.Watchlist;
import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
    @Autowired
    private UserService userService;

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private WatchlistMovieService watchlistMovieService;

    @GetMapping("/")
    public String welcome(){
        try {
//            Watchlist watchlist=new Watchlist(userService.findByEmail("alexandragazda@yahoo.com"));
////            watchlistService.save(watchlist);
////            System.out.println(watchlistService.findById("alexandragazda@yahoo.com"));
////            System.out.println(userService.findByEmail("alexandragazda@yahoo.com").getWatchlist().getID());
//            MovieWatchlist movieWatchlist=new MovieWatchlist(movieService.findById(5),watchlistService.findById("alexandragazda@yahoo.com"));
//            System.out.println(movieWatchlist.toString());
//            movieWatchlistService.save(movieWatchlist);
//            System.out.println(watchlistService.findById("alexandragazda@yahoo.com"));
//            watchlistService.delete();
//            MovieWatchlistDTO movieWatchlistDTO=new MovieWatchlistDTO(5,"tartageorge@outlook.com");
//            movieWatchlistService.save(movieWatchlistDTO);
//            movieWatchlistService.delete("alexandragazda@yahoo.com",2);
//            movieService.findAllByWatchlistID("alexandragazda@yahoo.com").forEach(x-> System.out.println(x.getMovie().getID() + " " + x.getFirstDate()));
        }
        catch (ValidationException e){
            System.out.println(e);
        }
        catch (ServiceException e){
            System.out.println(e);
        }

        return "welcome";
    }

    @GetMapping("/watchlists")
    public List<Watchlist> watchlists() {
        return watchlistService.findAll();
    }

//    @GetMapping("/moviewatchlists")
//    public List<MovieWatchlist> moviewatchlists() {
//        return movieWatchlistService.findAll();
//    }

//    @GetMapping("/watchlistmovies")
//    public ResponseEntity<List<WatchlistMovie>> watchlistmovies(@RequestParam("watchlistID") String watchlistID) {
//        List<WatchlistMovie> watchlistmovies = watchlistMovieService.findAllByWatchlistID(watchlistID);
//        return ResponseEntity.ok().body(watchlistmovies);
//    }

    @PostMapping("/watchlistmovies")
    public ResponseEntity<Integer> save(@RequestBody WatchlistMovieDTO watchlistMovieDTO) {
        try {
            WatchlistMovie watchlistMovie = watchlistMovieService.save(watchlistMovieDTO);

            return ResponseEntity.accepted().body(watchlistMovie.getID());
        }
        catch (ValidationException ex){
            System.out.println(ex);
            return ResponseEntity.status(422).body(-1); //validation error
        }
        catch (ServiceException ex){
            System.out.println(ex);
            return ResponseEntity.status(400).body(-1); //wrong data
        }
    }

    @GetMapping("/watchlistmovies")
    public ResponseEntity<Boolean> findAllByWatchlistIDAndMovieID(@RequestParam("watchlistID") String watchlistID,
                                                                  @RequestParam("movieID") Integer movieID){
        Boolean isInWatchlist = watchlistMovieService.findAllByWatchlistIDAndMovieID(watchlistID,movieID);
        return ResponseEntity.ok().body(isInWatchlist);
    }

    @DeleteMapping("/watchlistmovies")
    public ResponseEntity delete(@RequestParam("watchlistID") String watchlistID,
                                 @RequestParam("movieID") Integer movieID){
        try {
            watchlistMovieService.delete(watchlistID, movieID);

            return ResponseEntity.status(200).build();
        }
        catch (ServiceException ex){
            System.out.println(ex);
            return ResponseEntity.status(400).build(); //wrong data
        }
    }
}
