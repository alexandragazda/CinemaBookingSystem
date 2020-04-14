package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Ticket;
import com.cinema.cinemaserver.service.TicketService;
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

//    @GetMapping("/")
//    public String welcome(){
//        return "welcome";
//    }

    @GetMapping("/tickets")
    public List<Ticket> tickets() {
        return ticketService.findAll();
    }

}
