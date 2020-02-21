package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.dtos.TicketTypeDTO;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;

import java.util.List;

public interface TicketTypeService {
    List<TicketType> findAll();

    TicketTypeDTO getTicketTypeListAndNrAvailableTickets(Integer showtimeID);

    TicketType save(TicketType ticketType);

    TicketType findByID(TicketTypeEnum ID);
}
