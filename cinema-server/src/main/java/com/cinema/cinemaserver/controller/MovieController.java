package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.dtos.MovieDTO;
import com.cinema.cinemaserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Movie> movies = movieService.findAllByDate(date);
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Integer id){
        Movie movie=movieService.findById(id);
        if(movie==null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping("/movieswatchlist")
    public ResponseEntity<List<MovieDTO>> findById(@RequestParam String watchlistID){
        List<MovieDTO> movieDTOS=movieService.findAllByWatchlistID(watchlistID);
        return ResponseEntity.ok().body(movieDTOS);
    }

    @GetMapping("/coming-soon-movies/{month}")
    public ResponseEntity<List<MovieDTO>> comingSoon(@PathVariable Integer month){
        List<MovieDTO> movieDTOS=movieService.comingSoon(month);
        return ResponseEntity.ok().body(movieDTOS);
    }

    @GetMapping("/available-movies")
    public ResponseEntity<List<MovieDTO>> available(){
        List<MovieDTO> movieDTOS=movieService.findAllAvailable();
        return ResponseEntity.ok().body(movieDTOS);
    }

    @GetMapping("/top-movies")
    public ResponseEntity<List<MovieDTO>> top(){
        List<MovieDTO> movieDTOS=movieService.findTop();
        return ResponseEntity.ok().body(movieDTOS);
    }
}
