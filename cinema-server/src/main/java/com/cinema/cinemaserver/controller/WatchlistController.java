package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.WatchlistMovie;
import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.UserUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
    @Autowired
    private WatchlistMovieService watchlistMovieService;

    @PostMapping("/watchlistmovies")
    public ResponseEntity<Integer> save(@RequestBody WatchlistMovieDTO watchlistMovieDTO) {
        try {
            WatchlistMovie watchlistMovie = watchlistMovieService.save(watchlistMovieDTO);

            return ResponseEntity.accepted().body(watchlistMovie.getID());
        }
        catch (ValidationException ex){
            return ResponseEntity.status(422).body(-1); //validation error
        }
        catch (ServiceException ex){
            return ResponseEntity.status(400).body(-1); //wrong data
        }
    }

    @GetMapping("/watchlistmovies")
    public ResponseEntity<Boolean> findAllByWatchlistIDAndMovieID(@RequestParam("movieID") Integer movieID,
                                                                  @RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        Boolean isInWatchlist = watchlistMovieService.findAllByWatchlistIDAndMovieID(decoded.getSubject(),movieID);
        return ResponseEntity.ok().body(isInWatchlist);
    }

    @DeleteMapping("/watchlistmovies")
    public ResponseEntity delete(@RequestParam("movieID") Integer movieID,
                                 @RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        try {
            watchlistMovieService.delete(decoded.getSubject(), movieID);

            return ResponseEntity.status(200).build();
        }
        catch (ServiceException ex){
            return ResponseEntity.status(400).build(); //wrong data
        }
    }
}
