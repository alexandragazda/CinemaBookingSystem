package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;
import com.cinema.cinemaserver.domain.enums.AgeRating;
import com.cinema.cinemaserver.domain.enums.Technology;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.domain.utils.OrderItem;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GeneralController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private TicketTypeService ticketTypeService;
    @Autowired
    private ConcessionTypeService concessionTypeService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PlacedOrderService placedOrderService;
    @Autowired
    private WatchlistMovieService watchlistMovieService;

    @GetMapping("/")
    public String welcome() {
//        roleService.save(new Role("ROLE_USER"));
//        roleService.save(new Role("ROLE_ADMIN"));
//
//        userService.save(new User("alexandragazda@yahoo.com","aleoscar25","Alexandra","Gazda","0729094605"));
//        userService.save(new User("tartageorge@outlook.com","aleoscar25","George","Tarta","0756350487"));
//        userService.save(new User("agneta.gazda@gmail.com","aleoscar25","Agneta","Gazda",""));
//
//        screenService.save(new Screen(1,80,10,8));
//        screenService.save(new Screen(2,70,10,7));
//        screenService.save(new Screen(3,100,10,10));
//
//        try {
//            //playing now
//            File file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\LikeABoss.jpg");
//            Movie movie1=new Movie("Like a Boss","Comedy","Salma Hayek, Rose Byrne, Tiffany Haddish","Miguel Arteta",83, AgeRating.AP12, LocalDate.of(2020,4,30),LocalDate.of(2020,8,10),"2D, 3D", "https://www.imdb.com/title/tt7545266/","quiGRsBwrJ8", Files.readAllBytes(file.toPath()),"Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Bombshell.jpg");
//            Movie movie2=new Movie("Bombshell","Biography, Drama","Charlize Theron, Nicole Kidman, Margot Robbie","Jay Roach",109,AgeRating.AP12,LocalDate.of(2020,5,25),LocalDate.of(2020,7,30),"2D, 3D", "https://www.imdb.com/title/tt6394270/","0rBnkBIhoFE",Files.readAllBytes(file.toPath()),"A group of women take on Fox News head Roger Ailes and the toxic atmosphere he presided over at the network.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheGentlemen.jpg");
//            Movie movie3=new Movie("The Gentlemen"," Action, Crime","Matthew McConaughey, Charlie Hunnam, Michelle Dockery","Guy Ritchie",113,AgeRating.N15,LocalDate.of(2020,5,29),LocalDate.of(2020,8,2), "2D, 3D", "https://www.imdb.com/title/tt8367814/","Ify9S7hj480",Files.readAllBytes(file.toPath()),"An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BirdsOfPrey.jpg");
//            Movie movie4=new Movie("Birds of Prey","Action, Adventure, Crime","Margot Robbie, Rosie Perez, Mary Elizabeth Winstead","Cathy Yan",109,AgeRating.N15,LocalDate.of(2020,6,1),LocalDate.of(2020,8,1),"2D","https://www.imdb.com/title/tt7713068/","x3HbbzHK5Mc",Files.readAllBytes(file.toPath()),"After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheInvisibleMan.jpg");
//            Movie movie5=new Movie("The Invisible Man","Horror, Mystery, Sci-Fi","Elisabeth Moss, Oliver Jackson-Cohen, Harriet Dyer", "Leigh Whannell",124,AgeRating.N15,LocalDate.of(2020,6,5),LocalDate.of(2020,7,25),"2D, 3D","https://www.imdb.com/title/tt1051906/","Pso0Aj_cTh0",Files.readAllBytes(file.toPath()),"When Cecilia's abusive ex takes his own life and leaves her his fortune, she suspects his death was a hoax. As a series of coincidences turn lethal, Cecilia works to prove that she is being hunted by someone nobody can see.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Dolittle.jpg");
//            Movie movie6=new Movie("Dolittle","Adventure, Comedy, Family","Robert Downey Jr., Antonio Banderas, Michael Sheen","Stephen Gaghan",101,AgeRating.AG,LocalDate.of(2020,5,30),LocalDate.of(2020,8,5),"2D","https://www.imdb.com/title/tt6673612/","hej47fWFLQs",Files.readAllBytes(file.toPath()),"A physician who can talk to animals embarks on an adventure to find a legendary island with a young apprentice and a crew of strange pets.");
//
//            //coming soon
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\DreamHorse.jpg");
//            Movie movie7=new Movie("Dream Horse","Comedy, Drama, Sport","Toni Collette, Damian Lewis, Joanna Page","Euros Lyn",113,AgeRating.AG,LocalDate.of(2020,8,1),LocalDate.of(2020,10,1),"2D","https://www.imdb.com/title/tt9883996/","ty_DAhC_CLc",Files.readAllBytes(file.toPath()),"Dream Alliance, an unlikely race horse bred by small town Welsh bartender, Jan Vokes. With no experience, Jan convinces her neighbors to chip in their meager earnings to help raise Dream in the hopes he can compete with the racing elites.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Scoob.jpg");
//            Movie movie8=new Movie("Scoob!","Animation, Adventure, Comedy","Mark Wahlberg, Zac Efron, Mckenna Grace","Tony Cervone",120,AgeRating.AG,LocalDate.now().plusDays(3),LocalDate.of(2020,8,25),"2D, 3D","https://www.imdb.com/title/tt3152592/","ZnKvQbpDYXU",Files.readAllBytes(file.toPath()),"Scooby and the gang face their most challenging mystery ever: a plot to unleash the ghost dog Cerberus upon the world. As they race to stop this dogpocalypse, the gang discovers that Scooby has an epic destiny greater than anyone imagined.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheWomanInTheWindow.jpg");
//            Movie movie9=new Movie("The Woman in the Window","Crime, Drama, Mystery","Amy Adams, Gary Oldman, Jennifer Jason Leigh","Joe Wright",120,AgeRating.N15,LocalDate.now().plusDays(6),LocalDate.of(2020,8,20),"2D","https://www.imdb.com/title/tt6111574/","J0hTmzISOlQ",Files.readAllBytes(file.toPath()),"An agoraphobic woman living alone in New York begins spying on her new neighbors, only to witness a disturbing act of violence.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\FreeGuy.jpg");
//            Movie movie10=new Movie("Free Guy","Action, Adventure, Comedy","Taika Waititi, Jodie Comer, Ryan Reynolds","Shawn Levy",110,AgeRating.AP12,LocalDate.now().plusDays(1),LocalDate.of(2020,9,1),"2D","https://www.imdb.com/title/tt6264654/","X2m-08cOAbc",Files.readAllBytes(file.toPath()),"A bank teller discovers that he's actually an NPC inside a brutal, open world video game.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheConjuring3.jpg");
//            Movie movie11=new Movie("The Conjuring 3","Horror, Mystery, Thriller","Vera Farmiga, Patrick Wilson, Julian Hilliard","Michael Chaves",90,AgeRating.IM18,LocalDate.of(2020,8,2),LocalDate.of(2020,9,27),"2D, 3D","https://www.imdb.com/title/tt7069210/","7g9VWKczN8g",Files.readAllBytes(file.toPath()),"Reveals a chilling story of terror, murder and unknown evil that shocked even experienced real-life paranormal investigators Ed and Lorraine Warren (Patrick Wilson and Vera Farmiga).");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\SeaFever.jpg");
//            Movie movie12=new Movie("Sea Fever", "Horror, Sci-Fi", "Connie Nielsen, Hermione Corfield, Dougray Scott","Neasa Hardiman",95,AgeRating.N15,LocalDate.of(2020,9,5),LocalDate.of(2020,10,25),"2D, 3D","https://www.imdb.com/title/tt2716382/","OEgysK5W5bI",Files.readAllBytes(file.toPath()),"The crew of a West of Ireland trawler, marooned at sea, struggle for their lives against a growing parasite in their water supply.");
//
//            //history
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BadBoysForLife.jpg");
//            Movie movie13=new Movie("Bad Boys for Life","Action, Comedy, Crime","Will Smith, Martin Lawrence, Vanessa Hudgens","Adil El Arbi (as Adil), Bilall Fallah (as Bilall)",124,AgeRating.N15,LocalDate.of(2020,3,20),LocalDate.of(2020,5,25), "2D, 3D", "https://www.imdb.com/title/tt1502397/","jKCj3XuPG8M",Files.readAllBytes(file.toPath()),"The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
//
//            movieService.save(movie1);
//            movieService.save(movie2);
//            movieService.save(movie3);
//            movieService.save(movie4);
//            movieService.save(movie5);
//            movieService.save(movie6);
//
//            movieService.save(movie7);
//            movieService.save(movie8);
//            movieService.save(movie9);
//            movieService.save(movie10);
//            movieService.save(movie11);
//            movieService.save(movie12);
//
//            movieService.save(movie13);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //today-2
//        showtimeService.save(new Showtime(LocalDate.now().minusDays(2), LocalTime.of(16,30), Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(1)));
//
//        //today-1
//        showtimeService.save(new Showtime(LocalDate.now().minusDays(1), LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//
//        //today
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(10,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(18,00),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(19,15),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,15),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(11,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(19,30),Technology.tec_3D,movieService.findByTitle("The Invisible Man"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(16,00),Technology.tec_2D,movieService.findByTitle("The Invisible Man"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(14,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(2)));
//
//        //today + 1
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(16,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(17, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_3D,movieService.findByTitle("The Invisible Man"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(13,30),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(2)));
//
//        //today + 2
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(19,00),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(19,30),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(20,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(14,30), Technology.tec_3D,movieService.findByTitle("Like a Boss"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(17, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(22, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(21,30),Technology.tec_2D,movieService.findByTitle("The Invisible Man"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(12,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(1)));
//
//        //today + 3
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(15,00),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(22,00),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(20,30),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(18,00),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(20,00), Technology.tec_3D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(16, 20), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(22, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(18,30),Technology.tec_3D,movieService.findByTitle("The Invisible Man"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(11,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(13,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(12, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3),LocalTime.of(15, 30), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(2)));
//
////        //today + 4
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(21,10),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(19,00),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(19,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(20,30), Technology.tec_3D,movieService.findByTitle("Like a Boss"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(22, 40), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(17,00),Technology.tec_2D,movieService.findByTitle("The Invisible Man"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(21,30),Technology.tec_3D,movieService.findByTitle("The Invisible Man"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(15,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(17,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(12, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4),LocalTime.of(14, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(2)));
//
//        //today + 5
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(20,15),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(20,30),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(18,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(16, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(22,00),Technology.tec_3D,movieService.findByTitle("The Invisible Man"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(18,00),Technology.tec_2D,movieService.findByTitle("Dolittle"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5),LocalTime.of(16, 00), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(1)));
//
//        //today + 6
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6),LocalTime.of(22,10),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6),LocalTime.of(16,00),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6),LocalTime.of(19,00),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6),LocalTime.of(16,00), Technology.tec_3D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6),LocalTime.of(21,00),Technology.tec_2D,movieService.findByTitle("The Invisible Man"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(20, 00), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(18, 00), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(22, 20), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(15, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(18, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(12, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(13, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(1)));
//
//
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Child,17.0,19.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Student,18.5,20.5));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Adult,22.0,24.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Retired,20.0,22.0));
//
//
//        concessionTypeService.save(new ConcessionType("Food"));
//        concessionTypeService.save(new ConcessionType("Drink"));
//        concessionTypeService.save(new ConcessionType("Combo"));

        return "welcome";
    }
}
