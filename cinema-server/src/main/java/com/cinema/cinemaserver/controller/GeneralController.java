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
//        userService.save(new User("tartageorge@outlook.com","spikeadi55","George","Tarta","0756350487"));
//        userService.save(new User("agneta.gazda@gmail.com","agnetagazda","Agneta","Gazda",""));
//
//        screenService.save(new Screen(1,80,10,8));
//        screenService.save(new Screen(2,70,10,7));
//        screenService.save(new Screen(3,100,10,10));
//
//        try {
//            //playing now
//            File file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\LikeABoss.jpg");
//            Movie movie1=new Movie("Like a Boss","Comedy","Salma Hayek, Rose Byrne, Tiffany Haddish","Miguel Arteta",83, AgeRating.AP12, LocalDate.of(2020,3,30),LocalDate.of(2020,5,26),"2D, 3D", "https://www.imdb.com/title/tt7545266/","quiGRsBwrJ8", Files.readAllBytes(file.toPath()),"Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Bombshell.jpg");
//            Movie movie2=new Movie("Bombshell","Biography, Drama","Charlize Theron, Nicole Kidman, Margot Robbie","Jay Roach",109,AgeRating.AP12,LocalDate.of(2019,4,2),LocalDate.of(2020,6,20),"2D, 3D", "https://www.imdb.com/title/tt6394270/","0rBnkBIhoFE",Files.readAllBytes(file.toPath()),"A group of women take on Fox News head Roger Ailes and the toxic atmosphere he presided over at the network.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheGentlemen.jpg");
//            Movie movie3=new Movie("The Gentlemen"," Action, Crime","Matthew McConaughey, Charlie Hunnam, Michelle Dockery","Guy Ritchie",113,AgeRating.N15,LocalDate.of(2020,4,10),LocalDate.of(2020,6,10), "2D, 3D", "https://www.imdb.com/title/tt8367814/","Ify9S7hj480",Files.readAllBytes(file.toPath()),"An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BirdsOfPrey.jpg");
//            Movie movie4=new Movie("Birds of Prey","Action, Adventure, Crime","Margot Robbie, Rosie Perez, Mary Elizabeth Winstead","Cathy Yan",109,AgeRating.N15,LocalDate.now(),LocalDate.of(2020,7,25),"2D","https://www.imdb.com/title/tt7713068/","x3HbbzHK5Mc",Files.readAllBytes(file.toPath()),"After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.");
//
//            //coming soon
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\DreamHorse.jpg");
//            Movie movie5=new Movie("Dream Horse","Comedy, Drama, Sport","Toni Collette, Damian Lewis, Joanna Page","Euros Lyn",113,AgeRating.AG,LocalDate.of(2020,5,1),LocalDate.of(2020,8,1),"2D","https://www.imdb.com/title/tt9883996/","ty_DAhC_CLc",Files.readAllBytes(file.toPath()),"Dream Alliance, an unlikely race horse bred by small town Welsh bartender, Jan Vokes. With no experience, Jan convinces her neighbors to chip in their meager earnings to help raise Dream in the hopes he can compete with the racing elites.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Scoob.jpg");
//            Movie movie6=new Movie("Scoob!","Animation, Adventure, Comedy","Mark Wahlberg, Zac Efron, Mckenna Grace","Tony Cervone",120,AgeRating.AG,LocalDate.now().plusDays(3),LocalDate.of(2020,7,25),"2D, 3D","https://www.imdb.com/title/tt3152592/","ZnKvQbpDYXU",Files.readAllBytes(file.toPath()),"Scooby and the gang face their most challenging mystery ever: a plot to unleash the ghost dog Cerberus upon the world. As they race to stop this dogpocalypse, the gang discovers that Scooby has an epic destiny greater than anyone imagined.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheWomanInTheWindow.jpg");
//            Movie movie7=new Movie("The Woman in the Window","Crime, Drama, Mystery","Amy Adams, Gary Oldman, Jennifer Jason Leigh","Joe Wright",120,AgeRating.N15,LocalDate.now().plusDays(6),LocalDate.of(2020,6,20),"2D","https://www.imdb.com/title/tt6111574/","J0hTmzISOlQ",Files.readAllBytes(file.toPath()),"An agoraphobic woman living alone in New York begins spying on her new neighbors, only to witness a disturbing act of violence.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\FreeGuy.jpg");
//            Movie movie8=new Movie("Free Guy","Action, Adventure, Comedy","Taika Waititi, Jodie Comer, Ryan Reynolds","Shawn Levy",110,AgeRating.AP12,LocalDate.now().plusDays(1),LocalDate.of(2020,6,1),"2D","https://www.imdb.com/title/tt6264654/","X2m-08cOAbc",Files.readAllBytes(file.toPath()),"A bank teller discovers that he's actually an NPC inside a brutal, open world video game.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheConjuring3.jpg");
//            Movie movie9=new Movie("The Conjuring 3","Horror, Mystery, Thriller","Vera Farmiga, Patrick Wilson, Julian Hilliard","Michael Chaves",90,AgeRating.IM18,LocalDate.of(2020,6,14),LocalDate.of(2020,8,27),"2D, 3D","https://www.imdb.com/title/tt7069210/","7g9VWKczN8g",Files.readAllBytes(file.toPath()),"Reveals a chilling story of terror, murder and unknown evil that shocked even experienced real-life paranormal investigators Ed and Lorraine Warren (Patrick Wilson and Vera Farmiga).");
//
//            //history
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BadBoysForLife.jpg");
//            Movie movie10=new Movie("Bad Boys for Life","Action, Comedy, Crime","Will Smith, Martin Lawrence, Vanessa Hudgens","Adil El Arbi (as Adil), Bilall Fallah (as Bilall)",124,AgeRating.N15,LocalDate.of(2020,2,18),LocalDate.of(2020,4,10), "2D, 3D", "https://www.imdb.com/title/tt1502397/","jKCj3XuPG8M",Files.readAllBytes(file.toPath()),"The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
//
//            movieService.save(movie1);
//            movieService.save(movie2);
//            movieService.save(movie3);
//            movieService.save(movie4);
//
//            movieService.save(movie5);
//            movieService.save(movie6);
//            movieService.save(movie7);
//            movieService.save(movie8);
//            movieService.save(movie9);
//
//            movieService.save(movie10);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        showtimeService.save(new Showtime(LocalDate.now().minusDays(1), LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().minusDays(2), LocalTime.of(16,30), Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(1)));
//
//        //today
//        showtimeService.save(new Showtime(LocalDate.now(), LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(10,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(18,00),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(19,15),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(11,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(3)));
//        //today + 1
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(16,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1), LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1), LocalTime.of(17, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//
//        //today + 2
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//
//        //today + 3
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3), LocalTime.of(12, 30), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3), LocalTime.of(15, 30), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(3), LocalTime.of(22, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//
//        //today + 4
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4), LocalTime.of(16, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(4), LocalTime.of(19, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//
//        //today + 5
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5), LocalTime.of(16, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(5), LocalTime.of(23, 00), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(2)));
//
//        //today + 6
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(20, 20), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(18, 00), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(22, 20), Technology.tec_2D, movieService.findByTitle("The Woman in the Window"), screenService.findByID(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(20, 30), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(17, 15), Technology.tec_2D, movieService.findByTitle("Free Guy"), screenService.findByID(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(12, 00), Technology.tec_2D, movieService.findByTitle("Scoob!"), screenService.findByID(3)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(6), LocalTime.of(14, 20), Technology.tec_3D, movieService.findByTitle("Scoob!"), screenService.findByID(1)));
//
//        //adaugate dupa
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,15),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findByID(1)));
//
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Child,17.0,19.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Student,18.5,20.5));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Adult,22.0,24.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Retired,20.0,22.0));
//
//        concessionTypeService.save(new ConcessionType("Food"));
//        concessionTypeService.save(new ConcessionType("Drink"));
//        concessionTypeService.save(new ConcessionType("Menu"));
//
//
//
//
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(0).getID(),"alexandragazda@yahoo.com",null,null,null,0,2,0,0,37.0,"'9:0,1"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(1).getID(),"alexandragazda@yahoo.com",null,null,null,0,1,1,0,40.5,"'5:3,4"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(8).getID(),"alexandragazda@yahoo.com",null,null,null,0,3,0,0,55.5,"'9:2,3"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(8).getID(),null,"alexandragazda@yahoo.com","Alexandra","Pop",0,2,2,0,81.0,"'7:1,2,3,4"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(11).getID(),"alexandragazda@yahoo.com",null,null,null,0,1,1,0,40.5,"'5:3,4"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(16).getID(),"alexandragazda@yahoo.com",null,null,null,0,2,0,0,37.0,"'2:1,2"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(18).getID(),"alexandragazda@yahoo.com",null,null,null,2,2,0,0,71.0,"'3:0,1,2,3"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(20).getID(),"alexandragazda@yahoo.com",null,null,null,0,1,1,0,40.5,"'7:7,8"));
//        bookingService.save(new BookingDTO(showtimeService.findAll().get(27).getID(),"alexandragazda@yahoo.com",null,null,null,0,0,2,0,44.0,"'8:5,6"));
//
//        List<OrderItem> orderItems=new ArrayList<>();
//        orderItems.add(new OrderItem(6,2));
//        orderItems.add(new OrderItem(21,2));
//        placedOrderService.save(new OrderDTO(showtimeService.findAll().get(18).getID(),null,"tartageorge@outlook.com","Adrian","Tarta",orderItems,26.0,LocalTime.of(12,10)));
//        placedOrderService.save(new OrderDTO(showtimeService.findAll().get(11).getID(),"alexandragazda@yahoo.com",null,null,null,orderItems,26.0,LocalTime.of(22,10)));
//
//        orderItems=new ArrayList<>();
//        orderItems.add(new OrderItem(28,1));
//        orderItems.add(new OrderItem(34,1));
//        placedOrderService.save(new OrderDTO(showtimeService.findAll().get(27).getID(),"alexandragazda@yahoo.com",null,null,null,orderItems,35.0,LocalTime.of(20,5)));
//
//        orderItems=new ArrayList<>();
//        orderItems.add(new OrderItem(14,1));
//        orderItems.add(new OrderItem(24,1));
//        orderItems.add(new OrderItem(31,2));
//        placedOrderService.save(new OrderDTO(showtimeService.findAll().get(25).getID(),"alexandragazda@yahoo.com",null,null,null,orderItems,47.0,LocalTime.of(20,15)));
//
//        orderItems=new ArrayList<>(); //history
//        orderItems.add(new OrderItem(4,2));
//        orderItems.add(new OrderItem(15,2));
//        placedOrderService.save(new OrderDTO(showtimeService.findAll().get(0).getID(),"alexandragazda@yahoo.com",null,null,null,orderItems,34.0,LocalTime.of(21,15)));
//
//
//        watchlistMovieService.save(new WatchlistMovieDTO("alexandragazda@yahoo.com",movieService.findByTitle("Birds of Prey").getID()));

        return "welcome";
    }
}
