package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.Screen;
import com.cinema.cinemaserver.domain.enums.AgeRating;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenService screenService;

//    @GetMapping("/")
//    public String welcome(){
//        screenService.save(new Screen(1,80,10,8));
//        screenService.save(new Screen(2,70,10,7));
//        screenService.save(new Screen(3,70,7,10));
//
//        try {
//            File file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\LikeABoss.jpg");
//            Movie movie1=new Movie("Like a Boss","Comedy","Salma Hayek, Rose Byrne, Tiffany Haddish","Miguel Arteta",83,AgeRating.AP12,LocalDate.of(2020,1,10),LocalDate.of(2020,2,26),"2D, 3D", "https://www.imdb.com/title/tt7545266/","quiGRsBwrJ8",Files.readAllBytes(file.toPath()),"Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Bombshell.jpg");
//            Movie movie2=new Movie("Bombshell","Biography, Drama","Charlize Theron, Nicole Kidman, Margot Robbie","Jay Roach",109,AgeRating.AP12,LocalDate.of(2019,12,20),LocalDate.of(2020,02,01),"2D, 3D", "https://www.imdb.com/title/tt6394270/","0rBnkBIhoFE",Files.readAllBytes(file.toPath()),"A group of women take on Fox News head Roger Ailes and the toxic atmosphere he presided over at the network.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheGentlemen.jpg");
//            Movie movie3=new Movie("The Gentlemen"," Action, Crime","Matthew McConaughey, Charlie Hunnam, Michelle Dockery","Guy Ritchie",113,AgeRating.N15,LocalDate.of(2020,1,30),LocalDate.of(2020,03,20), "2D, 3D", "https://www.imdb.com/title/tt8367814/","Ify9S7hj480",Files.readAllBytes(file.toPath()),"An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BadBoysForLife.jpg");
//            Movie movie4=new Movie("Bad Boys for Life","Action, Comedy, Crime","Will Smith, Martin Lawrence, Vanessa Hudgens","Adil El Arbi (as Adil), Bilall Fallah (as Bilall)",124,AgeRating.N15,LocalDate.of(2020,1,18),LocalDate.of(2020,03,20), "2D, 3D", "https://www.imdb.com/title/tt1502397/","jKCj3XuPG8M",Files.readAllBytes(file.toPath()),"The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BirdsOfPrey.jpg");
//            Movie movie5=new Movie("Birds of Prey","Action, Adventure, Crime","Margot Robbie, Rosie Perez, Mary Elizabeth Winstead","Cathy Yan",109,AgeRating.N15,LocalDate.now(),LocalDate.of(2020,03,25),"2D","https://www.imdb.com/title/tt7713068/","x3HbbzHK5Mc",Files.readAllBytes(file.toPath()),"After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.");
//
//            movieService.save(movie1);
//            movieService.save(movie2);
//            movieService.save(movie3);
//            movieService.save(movie4);
//            movieService.save(movie5);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "welcome";
//    }

//    @GetMapping("/movies")
//    public List<Movie> movies() {
//        return movieService.findAll();
//    }

    @GetMapping("/screens")
    public List<Screen> screens() {
        return screenService.findAll();
    }

//    @GetMapping("/movies")
//    public List<Movie> movies() {
//        return movieService.findAll();
//    }

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

//    @GetMapping("/movies")
//    public ResponseEntity<List<Movie>> movies(){
//        System.out.println("da");
//        return ResponseEntity.ok().body(movieService.findAll());
//    }
}
