package com.cinema.cinemaserver.domain;

import javax.persistence.*;

@Entity
public class Ticket implements HasID<Integer>{

    private static final long serialVersionUID = 3413271940040911581L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private Double price;
    private Integer line;
    private Integer col;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name="ticket_type")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Booking booking;

    public Ticket() { }

    public Ticket(Double price, Integer line, Integer col, TicketType ticketType, Booking booking) {
        this.price = price;
        this.line = line;
        this.col = col;
        this.ticketType = ticketType;
        this.booking = booking;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Ticket: " +
                "ID=" + ID + " | " +
                "price=" + price + " | " +
                "line=" + line + " | " +
                "col=" + col + " | " +
                "ticketType=" + ticketType.getID() + " | " +
                "booking=" + booking.getID();
    }
}
