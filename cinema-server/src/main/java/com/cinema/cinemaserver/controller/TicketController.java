package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.Ticket;
import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.service.BookingService;
import com.cinema.cinemaserver.service.TicketService;
import com.cinema.cinemaserver.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public String welcome(){
//        ticketService.save(new Ticket(17.0,5,2,ticketTypeService.findByID(TicketTypeEnum.Child),bookingService.findByID(20)));
        return "welcome";
    }

    @GetMapping("/tickets")
    public List<Ticket> tickets() {
        return ticketService.findAll();
    }

}
