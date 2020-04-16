package com.cinema.cinemaserver.domain;

import javax.persistence.*;

@Entity
public class PlacedOrderItem implements HasID<Integer>{

    private static final long serialVersionUID= 3374571328840909501L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
    private PlacedOrder placedOrder;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
    private Concession concession;

    public PlacedOrderItem() { }

    public PlacedOrderItem(Integer quantity, PlacedOrder placedOrder, Concession concession) {
        this.quantity = quantity;
        this.placedOrder = placedOrder;
        this.concession = concession;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PlacedOrder getPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(PlacedOrder placedOrder) {
        this.placedOrder = placedOrder;
    }

    public Concession getConcession() {
        return concession;
    }

    public void setConcession(Concession concession) {
        this.concession = concession;
    }

    @Override
    public String toString() {
        return "ConcessionOrder: " +
                "ID=" + ID + " | " +
                "quantity=" + quantity + " | " +
                "placedOrder=" + placedOrder.getID() + " | " +
                "concession=" + concession.getID();
    }
}
