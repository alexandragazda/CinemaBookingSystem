package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.Screen;
import com.cinema.cinemaserver.domain.dtos.MovieDTO;
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
////        screenService.save(new Screen(1,80,10,8));
////        screenService.save(new Screen(2,70,10,7));
////        screenService.save(new Screen(3,70,7,10));
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
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\DreamHorse.jpg");
//            Movie movie6=new Movie("Dream Horse","Comedy, Drama, Sport","Toni Collette, Damian Lewis, Joanna Page","Euros Lyn",113,AgeRating.AG,LocalDate.of(2020,5,1),LocalDate.of(2020,8,1),"2D","https://www.imdb.com/title/tt9883996/","ty_DAhC_CLc",Files.readAllBytes(file.toPath()),"Dream Alliance, an unlikely race horse bred by small town Welsh bartender, Jan Vokes. With no experience, Jan convinces her neighbors to chip in their meager earnings to help raise Dream in the hopes he can compete with the racing elites.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Scoob.jpg");
//            Movie movie7=new Movie("Scoob!","Animation, Adventure, Comedy","Mark Wahlberg, Zac Efron, Mckenna Grace","Tony Cervone",120,AgeRating.AG,LocalDate.of(2020,3,22),LocalDate.of(2020,5,25),"2D, 3D","https://www.imdb.com/title/tt3152592/","ZnKvQbpDYXU",Files.readAllBytes(file.toPath()),"Scooby and the gang face their most challenging mystery ever: a plot to unleash the ghost dog Cerberus upon the world. As they race to stop this dogpocalypse, the gang discovers that Scooby has an epic destiny greater than anyone imagined.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheWomanInTheWindow.jpg");
//            Movie movie8=new Movie("The Woman in the Window","Crime, Drama, Mystery","Amy Adams, Gary Oldman, Jennifer Jason Leigh","Joe Wright",120,AgeRating.N15,LocalDate.of(2020,3,25),LocalDate.of(2020,5,20),"2D","https://www.imdb.com/title/tt6111574/","J0hTmzISOlQ",Files.readAllBytes(file.toPath()),"An agoraphobic woman living alone in New York begins spying on her new neighbors, only to witness a disturbing act of violence.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\FreeGuy.jpg");
//            Movie movie9=new Movie("Free Guy","Action, Adventure, Comedy","Taika Waititi, Jodie Comer, Ryan Reynolds","Shawn Levy",110,AgeRating.AP12,LocalDate.of(2020,3,20),LocalDate.of(2020,6,1),"2D","https://www.imdb.com/title/tt6264654/","X2m-08cOAbc",Files.readAllBytes(file.toPath()),"A bank teller discovers that he's actually an NPC inside a brutal, open world video game.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheConjuring3.jpg");
//            Movie movie10=new Movie("The Conjuring 3","Horror, Mystery, Thriller","Vera Farmiga, Patrick Wilson, Julian Hilliard","Michael Chaves",90,AgeRating.IM18,LocalDate.of(2020,4,14),LocalDate.of(2020,6,27),"2D, 3D","https://www.imdb.com/title/tt7069210/","7g9VWKczN8g",Files.readAllBytes(file.toPath()),"Reveals a chilling story of terror, murder and unknown evil that shocked even experienced real-life paranormal investigators Ed and Lorraine Warren (Patrick Wilson and Vera Farmiga).");
//            //            movieService.save(movie1);
////            movieService.save(movie2);
////            movieService.save(movie3);
////            movieService.save(movie4);
////            movieService.save(movie5);
//
////              movieService.save(movie6);
////              movieService.save(movie7);
////              movieService.save(movie8);
////              movieService.save(movie9);
////              movieService.save(movie10);
//
//            movieService.findAllByReleaseMonth("04").forEach(x-> System.out.println(x.getID()));
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

    @GetMapping("/movieswatchlist")
    public ResponseEntity<List<MovieDTO>> findById(@RequestParam String watchlistID){
        List<MovieDTO> movieDTOS=movieService.findAllByWatchlistID(watchlistID);
        return ResponseEntity.ok().body(movieDTOS);
    }

    @GetMapping("/comingSoonMovies/{month}")
    public ResponseEntity<List<MovieDTO>> comingSoon(@PathVariable Integer month){
        List<MovieDTO> movieDTOS=movieService.comingSoon(month);
        return ResponseEntity.ok().body(movieDTOS);
    }
}
