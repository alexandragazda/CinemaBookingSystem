package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.dtos.BookingInfoDTO;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.BookingUtils;
import com.cinema.cinemaserver.utils.UserUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingUtils bookingUtils;

    @GetMapping("/bookings") //vine sters
    public List<Booking> bookings() {
        return bookingService.findAll();
    }

    @GetMapping("/seats")
    public ResponseEntity<List<List<Integer>>> seats(@RequestParam("showtimeID") Integer showtimeID) {
        List<List<Integer>> seats= bookingUtils.stateOfSeats(showtimeID);
        return ResponseEntity.ok().body(seats);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Integer> save(@RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking=bookingService.save(bookingDTO);

            return ResponseEntity.accepted().body(booking.getID());
        }
        catch (ValidationException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(422).body(-1); //validation error
        }
        catch (ServiceException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(400).body(-1); //wrong data
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(500).body(-1); //wrong data
        }
    }

    @GetMapping("/nrAvailableSeats")
    public ResponseEntity<Integer> getNrAvailableSeats(@RequestParam("showtimeID") Integer showtimeID) {
        Integer nrAvailableSeats= bookingUtils.getNrAvailableSeats(showtimeID);
        return ResponseEntity.ok().body(nrAvailableSeats);
    }

    @GetMapping("/expiredBookings")
    public ResponseEntity<List<BookingInfoDTO>> getExpiredBookings(@RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        List<BookingInfoDTO> expiredBookings = bookingService.findFirstExpiredBookings(decoded.getSubject());
        return ResponseEntity.ok().body(expiredBookings);
    }

    @GetMapping("/validBookings")
    public ResponseEntity<List<BookingInfoDTO>> getValidBookings(@RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        List<BookingInfoDTO> validBookings = bookingService.findValidBookings(decoded.getSubject());
        return ResponseEntity.ok().body(validBookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            bookingService.delete(id);
            return ResponseEntity.status(200).build();
        }
        catch (ServiceException ex){
            return ResponseEntity.status(404).build(); //wrong id
        }
    }
}
