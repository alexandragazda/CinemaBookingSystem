package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.repository.TicketTypeRepository;
import com.cinema.cinemaserver.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeServiceImplementation implements TicketTypeService {
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Override
    public List<TicketType> findAll() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public TicketType save(TicketType ticketType) {
        ticketTypeRepository.save(ticketType);

        return ticketType;
    }
}
