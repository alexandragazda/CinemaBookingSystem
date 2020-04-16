package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Concession implements HasID<Integer>{

    private static final long serialVersionUID = 7743271941140922781L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private String name;
    private String description;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "concession_type")
    private ConcessionType concessionType;

    @JsonIgnore
    @OneToMany(mappedBy = "concession", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<PlacedOrderItem> placedOrderItems=new HashSet<>();

    public Concession() { }

    public Concession(String name, String description, Double price, ConcessionType concessionType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.concessionType = concessionType;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ConcessionType getConcessionType() {
        return concessionType;
    }

    public void setConcessionType(ConcessionType concessionType) {
        this.concessionType = concessionType;
    }

    public Set<PlacedOrderItem> getPlacedOrderItems() {
        return placedOrderItems;
    }

    public void addPlacedOrderItem(PlacedOrderItem placedOrderItem) {
        placedOrderItems.add(placedOrderItem);
        placedOrderItem.setConcession(this);
    }

    public void removePlacedOrderItem(PlacedOrderItem placedOrderItem) {
        placedOrderItems.remove(placedOrderItem);
        placedOrderItem.setConcession(null);
    }

    @Override
    public String toString() {
        return "Concession: " +
                "ID=" + ID + " | " +
                "name=" + name + " | " +
                "description=" + description + " | " +
                "price=" + price + " | " +
                "concessionType=" + concessionType.getID();
    }
}
