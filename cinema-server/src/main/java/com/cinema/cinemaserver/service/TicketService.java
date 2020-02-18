package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Ticket;
import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Ticket save(Ticket ticket);

    Ticket findByID(Integer ID);
}
