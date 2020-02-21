package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.TicketType;

import java.util.List;

public class TicketTypeDTO {
    private List<TicketType> ticketTypeList;
    private Integer nrAvailableTickets;

    public TicketTypeDTO() { }

    public TicketTypeDTO(List<TicketType> ticketTypeList, Integer nrAvailableTickets) {
        this.ticketTypeList = ticketTypeList;
        this.nrAvailableTickets = nrAvailableTickets;
    }

    public List<TicketType> getTicketTypeList() {
        return ticketTypeList;
    }

    public void setTicketTypeList(List<TicketType> ticketTypeList) {
        this.ticketTypeList = ticketTypeList;
    }

    public Integer getNrAvailableTickets() {
        return nrAvailableTickets;
    }

    public void setNrAvailableTickets(Integer nrAvailableTickets) {
        this.nrAvailableTickets = nrAvailableTickets;
    }
}
