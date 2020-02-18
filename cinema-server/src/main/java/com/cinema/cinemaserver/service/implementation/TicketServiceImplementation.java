package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Ticket;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.TicketRepository;
import com.cinema.cinemaserver.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImplementation implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private Validator<Ticket> validator;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket save(Ticket ticket) {
        validator.validate(ticket); //validates the given ticket

        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public Ticket findByID(Integer ID) {
        if(ticketRepository.findById(ID).isPresent()) return ticketRepository.findById(ID).get();
        return null;
    }
}
