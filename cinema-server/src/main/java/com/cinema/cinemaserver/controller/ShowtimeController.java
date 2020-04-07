package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.enums.Technology;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ScreenService;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenService screenService;

//    @GetMapping
//    public String welcome() {
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14), LocalTime.of(20,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(18,00),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(10,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,14),LocalTime.of(20,30),Technology.tec_3D,movieService.findByTitle("Birds of Prey"),screenService.findById(3)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,15),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,15),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,15),LocalTime.of(16,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findById(1)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020,02,16),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findById(2)));
//
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 20), LocalTime.of(17, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 20), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(3)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 21), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(3)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 22), LocalTime.of(12, 30), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 22), LocalTime.of(15, 30), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 22), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 22), LocalTime.of(22, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(2)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 23), LocalTime.of(16, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 23), LocalTime.of(19, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(2)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 24), LocalTime.of(16, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 24), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 24), LocalTime.of(23, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(2)));
////
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(20, 20), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findById(1)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(18, 00), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(22, 20), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(2)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(17, 15), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(12, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findById(3)));
////        showtimeService.save(new Showtime(LocalDate.of(2020, 3, 25), LocalTime.of(14, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findById(1)));
//
//        return "welcome";
//    }

//    @GetMapping("/showtimes")
//    public List<Showtime> showtimes(){return showtimeService.findAll();}

    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> findByMovieIdAndDate(
            @RequestParam("movieId") Integer movieId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Showtime> showtimes=showtimeService.findAllByMovieIdAndDate(movieId,date);
        return ResponseEntity.ok().body(showtimes);
    }
}
