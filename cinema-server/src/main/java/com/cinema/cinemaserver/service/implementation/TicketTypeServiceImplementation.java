package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.dtos.TicketTypeDTO;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.repository.TicketTypeRepository;
import com.cinema.cinemaserver.service.TicketTypeService;
import com.cinema.cinemaserver.utils.BookingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeServiceImplementation implements TicketTypeService {
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private BookingUtils bookingUtils;

    @Override
    public List<TicketType> findAll() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public TicketTypeDTO getTicketTypeListAndNrAvailableTickets(Integer showtimeID) {
        Integer nrAvailableTickets=bookingUtils.getNrAvailableSeats(showtimeID);

        TicketTypeDTO ticketTypeDTO=new TicketTypeDTO(findAll(),nrAvailableTickets);
        return ticketTypeDTO;
    }

    @Override
    public TicketType save(TicketType ticketType) {
        ticketTypeRepository.save(ticketType);

        return ticketType;
    }

    @Override
    public TicketType findByID(TicketTypeEnum ID) {
        if(ticketTypeRepository.findById(ID).isPresent()) return ticketTypeRepository.findById(ID).get();
        return null;
    }
}
