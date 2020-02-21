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
        List<List<Integer>> stateOfSeats= bookingUtils.stateOfSeats(showtimeID);

        Integer nrAvailableTickets=0;
        for(int i=0;i<stateOfSeats.size();i++){
            for(int j=0;j<stateOfSeats.get(0).size();j++){
                if(stateOfSeats.get(i).get(j)==0) nrAvailableTickets++;
            }
        }

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
