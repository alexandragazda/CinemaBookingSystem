package com.cinema.cinemaserver.domain;

import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TicketType implements HasID<TicketTypeEnum>{

    private static final long serialVersionUID = 7899271848840911581L;

    @Id
    private TicketTypeEnum type;

    private double price2D;
    private double price3D;

    public TicketType() { }

    public TicketType(TicketTypeEnum type, double price2D, double price3D) {
        this.type = type;
        this.price2D = price2D;
        this.price3D = price3D;
    }

    public TicketTypeEnum getID() {
        return type;
    }

    public void setID(TicketTypeEnum type) {
        this.type = type;
    }

    public double getPrice2D() {
        return price2D;
    }

    public void setPrice2D(double price2D) {
        this.price2D = price2D;
    }

    public double getPrice3D() {
        return price3D;
    }

    public void setPrice3D(double price3D) {
        this.price3D = price3D;
    }

    @Override
    public String toString() {
        return "TicketType: " +
                "type=" + type + " | " +
                "price2D=" + price2D + " | " +
                "price3D=" + price3D;
    }
}
