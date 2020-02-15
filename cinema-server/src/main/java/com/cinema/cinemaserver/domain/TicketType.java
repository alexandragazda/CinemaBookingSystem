package com.cinema.cinemaserver.domain;

import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TicketType implements HasID<TicketTypeEnum>{

    private static final long serialVersionUID = 7899271848840911581L;

    @Id
    private TicketTypeEnum type;

    private double price;

    public TicketType() { }

    public TicketType(TicketTypeEnum type, double price) {
        this.type = type;
        this.price = price;
    }

    public TicketTypeEnum getID() {
        return type;
    }

    public void setID(TicketTypeEnum type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketType: " +
                "type=" + type + "|" +
                "price=" + price;
    }
}
