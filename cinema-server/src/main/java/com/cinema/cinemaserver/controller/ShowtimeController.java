package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.enums.Technology;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ScreenService;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String welcome(){
        //showtimeService.findAllByMovieId(6).forEach(x-> System.out.println(x.getScreen()));
        //showtimeService.save(new Showtime(LocalDate.of(2015,7,30), LocalTime.of(16,30), Technology.tec_2D,movieService.findByTitle("Vacation"),screenService.findById(1)));
        //showtimeService.save(new Showtime(LocalDate.of(2015,7,30), LocalTime.of(20,30), Technology.tec_2D,movieService.findByTitle("Vacation"),screenService.findById(1)));
        //showtimeService.save(new Showtime(LocalDate.of(2015,7,30), LocalTime.of(20,30), Technology.tec_2D,movieService.findByTitle("Joker"),screenService.findById(2)));
        //showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(20,30),Technology.tec_2D,movieService.findByTitle("TheGrudge"),screenService.findById(1)));
        //showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(16,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(2)));
        //showtimeService.save(new Showtime(LocalDate.of(2020,1,14),LocalTime.of(16,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(2)));
        //showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(1)));
        //showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(1)));
        //showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Cats"),screenService.findById(2)));
        //showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(20,00),Technology.tec_3D,movieService.findByTitle("TheGrudge"),screenService.findById(2)));
        //System.out.println(showtimeService.getShowtimesByDate(LocalDate.now()).get(0).getMovie());
        return "welcome";
    }

    @GetMapping("/showtimes")
    public List<Showtime> showtimes(){return showtimeService.findAll();}
}
