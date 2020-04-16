package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.dtos.ShowtimeDTOS;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

//    @GetMapping("/showtimes")
//    public List<Showtime> showtimes(){return showtimeService.findAll();}

    @GetMapping("/showtimes")
    public ResponseEntity<ShowtimeDTOS> findByMovieIdAndDate(
            @RequestParam("movieId") Integer movieId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        ShowtimeDTOS showtimeDTOS=showtimeService.findShowtimeDTOSByMovieIdAndDate(movieId,date);
        return ResponseEntity.ok().body(showtimeDTOS);
    }
}
