package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.dtos.TicketTypeDTO;
import com.cinema.cinemaserver.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    @GetMapping("/tickettypes")
    public TicketTypeDTO ticketTypes(@RequestParam("showtimeID") Integer showtimeID){
        return ticketTypeService.getTicketTypeListAndNrAvailableTickets(showtimeID);
    }
}
