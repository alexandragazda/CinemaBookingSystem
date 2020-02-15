package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    @GetMapping
    public String welcome(){
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Child,17.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Student,18.5));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Adult,22.0));
//        ticketTypeService.save(new TicketType(TicketTypeEnum.Retired,20.0));

        return "welcome";
    }

    @GetMapping("/tickets")
    public List<TicketType> tickets(){return ticketTypeService.findAll();}
}
