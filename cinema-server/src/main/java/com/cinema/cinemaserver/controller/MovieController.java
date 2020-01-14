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
//        screenService.save(new Screen(1,50,10,5));
//        screenService.save(new Screen(2,60,10,6));
//
//        //movieService.delete();
//
//        System.out.println(movieService.findAllByDate(LocalDate.of(2015,7,30)).get(0));
//        System.out.println(movieService.findAllByDate(LocalDate.of(2015,7,30)).get(1));
//        try {
//            File file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Vacation.jpg");
//            Movie movie1=new Movie("Vacation","Adventure, Comedy"," Ed Helms, Christina Applegate, Skyler Gisondo", "John Francis Daley, Jonathan Goldstein",99, AgeRating.AP12, LocalDate.of(2015,7,29),LocalDate.of(2015,8,1),"https://www.imdb.com/title/tt1524930/","4cNmMLq9ZrQ", Files.readAllBytes(file.toPath()),"Rusty Griswold takes his own family on a road trip to \"Walley World\" in order to spice things up with his wife and reconnect with his sons.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Joker.jpg");
//            Movie movie2=new Movie("Joker","Crime, Drama, Thriller","Joaquin Phoenix, Robert De Niro, Zazie Beetz","Todd Phillips",122, AgeRating.AG.N15,LocalDate.of(2019,10,4),LocalDate.of(2019,12,01),"https://www.imdb.com/title/tt7286456/","zAGVQLHvwOY", Files.readAllBytes(file.toPath()),"In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheGrudge.jpg");
//            Movie movie3=new Movie("TheGrudge"," Horror, Mystery","Tara Westwood, Junko Bailey, David Lawrence Brown","Nicolas Pesce",94,AgeRating.IM18,LocalDate.of(2020,1,3),LocalDate.of(2020,2,25),"https://www.imdb.com/title/tt3612126/","nMc5B6UL5Z8",Files.readAllBytes(file.toPath()),"A house is cursed by a vengeful ghost that dooms those who enter it with a violent death.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\LikeABoss.jpg");
//            Movie movie4=new Movie("Like a Boss","Comedy","Salma Hayek, Rose Byrne, Tiffany Haddish","Miguel Arteta",83,AgeRating.AP12,LocalDate.of(2020,1,10),LocalDate.of(2020,2,26),"https://www.imdb.com/title/tt7545266/","quiGRsBwrJ8",Files.readAllBytes(file.toPath()),"Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Bombshell.jpg");
//            Movie movie5=new Movie("Bombshell","Biography, Drama","Charlize Theron, Nicole Kidman, Margot Robbie","Jay Roach",109,AgeRating.AP12,LocalDate.of(2019,12,20),LocalDate.of(2020,02,01),"https://www.imdb.com/title/tt6394270/","0rBnkBIhoFE0rBnkBIhoFE",Files.readAllBytes(file.toPath()),"A group of women take on Fox News head Roger Ailes and the toxic atmosphere he presided over at the network.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Cats.jpg");
//            Movie movie6=new Movie("Cats","Comedy, Drama, Family","Jennifer Hudson, Judi Dench, Taylor Swift","Tom Hooper",110,AgeRating.AP12,LocalDate.of(2019,12,20),LocalDate.of(2020,02,20),"https://www.imdb.com/title/tt5697572/?ref_=fn_al_tt_1","FtSd844cI7U",Files.readAllBytes(file.toPath()),"A tribe of cats called the Jellicles must decide yearly which one will ascend to the Heaviside Layer and come back to a new Jellicle life.");
//            //movieService.save(movie1);
//            //movieService.save(movie2);
//            //movieService.save(movie3);
//            //movieService.save(movie4);
//            //movieService.save(movie5);
//            //movieService.save(movie6);
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

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        System.out.println(date);
        List<Movie> movies = movieService.findAllByDate(date);
        return ResponseEntity.ok().body(movies);
    }

//    @GetMapping("/movies")
//    public ResponseEntity<List<Movie>> movies(){
//        System.out.println("da");
//        return ResponseEntity.ok().body(movieService.findAll());
//    }
}
