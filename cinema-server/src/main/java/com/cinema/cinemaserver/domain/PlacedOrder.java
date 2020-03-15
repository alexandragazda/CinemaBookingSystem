package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PlacedOrder implements HasID<Integer> {

    private static final long serialVersionUID = 2245271951940922781L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private Double totalPrice;
    private LocalTime pickUpTime;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "placedOrder", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<PlacedOrderItem> placedOrderItems=new HashSet<>();

    public PlacedOrder() { }

    public PlacedOrder(Double totalPrice, LocalTime pickUpTime, String customerEmail, String customerFirstName, String customerLastName, User user, Showtime showtime) {
        this.totalPrice = totalPrice;
        this.pickUpTime = pickUpTime;
        this.customerEmail = customerEmail;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.user = user;
        this.showtime = showtime;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PlacedOrderItem> getPlacedOrderItems() {
        return placedOrderItems;
    }

    public void addPlacedOrderItem(PlacedOrderItem placedOrderItem) {
        placedOrderItems.add(placedOrderItem);
        placedOrderItem.setPlacedOrder(this);
    }

    public void removePlacedOrderItem(PlacedOrderItem placedOrderItem) {
        placedOrderItems.remove(placedOrderItem);
        placedOrderItem.setPlacedOrder(null);
    }

    @Override
    public String toString() {
        if(user!=null) {
            return "PlacedOrder: " +
                    "ID=" + ID +
                    "totalPrice=" + totalPrice + " | " +
                    "pickUpTime=" + pickUpTime + " | " +
                    "showtime=" + showtime.getID() + " | " +
                    "user=" + user.getID() + " | " +
                    "customerEmail='" + customerEmail + " | " +
                    "customerFirstName='" + customerFirstName + " | " +
                    "customerLastName='" + customerLastName;
        }
        return "PlacedOrder: " +
                "ID=" + ID +
                "totalPrice=" + totalPrice + " | " +
                "pickUpTime=" + pickUpTime + " | " +
                "showtime=" + showtime.getID() + " | " +
                "user=" + user + " | " +
                "customerEmail='" + customerEmail + " | " +
                "customerFirstName='" + customerFirstName + " | " +
                "customerLastName='" + customerLastName;
    }
}
