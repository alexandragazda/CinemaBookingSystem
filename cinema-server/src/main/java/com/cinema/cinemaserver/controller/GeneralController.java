//package com.cinema.cinemaserver.controller;
//
//import com.cinema.cinemaserver.domain.*;
//import com.cinema.cinemaserver.domain.enums.AgeRating;
//import com.cinema.cinemaserver.domain.enums.Technology;
//import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
//import com.cinema.cinemaserver.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class GeneralController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private ScreenService screenService;
//    @Autowired
//    private MovieService movieService;
//    @Autowired
//    private ShowtimeService showtimeService;
//    @Autowired
//    private TicketTypeService ticketTypeService;
//    @Autowired
//    private ConcessionTypeService concessionTypeService;
//
//    @GetMapping("/")
//    public String welcome() {
//        roleService.save(new Role("ROLE_USER"));
//        roleService.save(new Role("ROLE_ADMIN"));
//
//        userService.save(new User("alexandragazda@yahoo.com","aleoscar25","Alexandra","Gazda","0729094605"));
//
//        screenService.save(new Screen(1,80,10,8));
//        screenService.save(new Screen(2,70,10,7));
//        screenService.save(new Screen(3,100,10,10));
//
//        try {
//            File file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\LikeABoss.jpg");
//            Movie movie1=new Movie("Like a Boss","Comedy","Salma Hayek, Rose Byrne, Tiffany Haddish","Miguel Arteta",83, AgeRating.AP12, LocalDate.of(2020,3,10),LocalDate.of(2020,5,26),"2D, 3D", "https://www.imdb.com/title/tt7545266/","quiGRsBwrJ8", Files.readAllBytes(file.toPath()),"Two friends with very different ideals start a beauty company together. One is more practical while the other wants to earn her fortune and live a lavish lifestyle.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\Bombshell.jpg");
//            Movie movie2=new Movie("Bombshell","Biography, Drama","Charlize Theron, Nicole Kidman, Margot Robbie","Jay Roach",109,AgeRating.AP12,LocalDate.of(2019,2,20),LocalDate.of(2020,5,01),"2D, 3D", "https://www.imdb.com/title/tt6394270/","0rBnkBIhoFE",Files.readAllBytes(file.toPath()),"A group of women take on Fox News head Roger Ailes and the toxic atmosphere he presided over at the network.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\TheGentlemen.jpg");
//            Movie movie3=new Movie("The Gentlemen"," Action, Crime","Matthew McConaughey, Charlie Hunnam, Michelle Dockery","Guy Ritchie",113,AgeRating.N15,LocalDate.of(2020,3,30),LocalDate.of(2020,4,20), "2D, 3D", "https://www.imdb.com/title/tt8367814/","Ify9S7hj480",Files.readAllBytes(file.toPath()),"An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BadBoysForLife.jpg");
//            Movie movie4=new Movie("Bad Boys for Life","Action, Comedy, Crime","Will Smith, Martin Lawrence, Vanessa Hudgens","Adil El Arbi (as Adil), Bilall Fallah (as Bilall)",124,AgeRating.N15,LocalDate.of(2020,3,18),LocalDate.of(2020,4,20), "2D, 3D", "https://www.imdb.com/title/tt1502397/","jKCj3XuPG8M",Files.readAllBytes(file.toPath()),"The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
//            file=new File("C:\\Users\\Alexandra\\Documents\\GitHub\\CinemaBookingSystem\\MoviePosters\\BirdsOfPrey.jpg");
//            Movie movie5=new Movie("Birds of Prey","Action, Adventure, Crime","Margot Robbie, Rosie Perez, Mary Elizabeth Winstead","Cathy Yan",109,AgeRating.N15,LocalDate.now(),LocalDate.of(2020,05,25),"2D","https://www.imdb.com/title/tt7713068/","x3HbbzHK5Mc",Files.readAllBytes(file.toPath()),"After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.");
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
//        showtimeService.save(new Showtime(LocalDate.now(), LocalTime.of(21,30), Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(2)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(18,00),Technology.tec_3D,movieService.findByTitle("Bombshell"),screenService.findById(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(3)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(10,30),Technology.tec_2D,movieService.findByTitle("Like a Boss"),screenService.findById(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
//        showtimeService.save(new Showtime(LocalDate.now(),LocalTime.of(20,45),Technology.tec_3D,movieService.findByTitle("Birds of Prey"),screenService.findById(3)));
//
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(12,30),Technology.tec_2D,movieService.findByTitle("Birds of Prey"),screenService.findById(1)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(22,30),Technology.tec_2D,movieService.findByTitle("Bombshell"),screenService.findById(2)));
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(1),LocalTime.of(16,00),Technology.tec_3D,movieService.findByTitle("The Gentlemen"),screenService.findById(1)));
//
//        showtimeService.save(new Showtime(LocalDate.now().plusDays(2),LocalTime.of(17,30),Technology.tec_2D,movieService.findByTitle("The Gentlemen"),screenService.findById(2)));
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
//        return "welcome";
//    }
//}
