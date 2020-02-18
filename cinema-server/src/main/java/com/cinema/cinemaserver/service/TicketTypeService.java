package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;

import java.util.List;

public interface TicketTypeService {
    List<TicketType> findAll();

    TicketType save(TicketType ticketType);

    TicketType findByID(TicketTypeEnum ID);
}
