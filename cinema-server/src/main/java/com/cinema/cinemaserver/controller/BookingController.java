package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScreenService screenService;

//    @GetMapping("/")
//    public String welcome(){
////        bookingService.save(new Booking("5:2,3;7:3",52.5,2,1,0,0,showtimeService.findById(16),null));
////        bookingService.save(new Booking("3:2,3",40.0,0,0,0,2,showtimeService.findById(16),userService.findByEmail("alexandragazda@yahoo.com")));
////        bookingService.findAll().forEach(x-> System.out.println(x.getShowtime().getScreen() + " " + x.getShowtime().getDate() + " " + x.getShowtime().getTime()));
////        System.out.println(screenService.findById(1));
////        bookingService.findAllByScreenIDAndDateAndTime(1, LocalDate.of(2020,02,15), LocalTime.of(12,30))
////                .forEach(x-> System.out.println(x));
////        bookingService.stateOfSeats(1, LocalDate.of(2020,02,15), LocalTime.of(12,30));
//        return "welcome";
//    }

    @GetMapping("/bookings")
    public List<Booking> bookings() {
        return bookingService.findAll();
    }

    @GetMapping("/seats")
    public ResponseEntity<List<List<Integer>>> seats(
            @RequestParam("screenID") Integer screenID,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time
    ) {
        List<List<Integer>> seats=bookingService.stateOfSeats(screenID,date,time);
        return ResponseEntity.ok().body(seats);
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> save(@RequestBody BookingDTO bookingDTO) {

        System.out.println(bookingDTO);

        Gson gson = new Gson();

        try {
            bookingService.save(bookingDTO);

            return ResponseEntity.accepted().body(gson.toJson("", String.class));
        }
        catch (ValidationException ex){
            System.out.println(ex);
            return ResponseEntity.status(422).body(gson.toJson(ex.getMessage(), String.class)); //validation error
        }
        catch (ServiceException ex){
            System.out.println(ex);
            return ResponseEntity.status(400).body(gson.toJson(ex.getMessage(), String.class)); //wrong data
        }
    }
}
