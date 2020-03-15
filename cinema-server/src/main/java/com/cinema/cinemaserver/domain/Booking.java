package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking implements HasID<Integer> {

    private static final long serialVersionUID= 3374571328840909501L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private String seats;
    private Double totalPrice;
    private Integer nrChildTickets;
    private Integer nrStudentTickets;
    private Integer nrAdultTickets;
    private Integer nrRetiredTickets;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
//    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@ID")
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
//    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@ID")
    private User user;

    @JsonIgnore //vine sters
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<Ticket> tickets=new HashSet<>();

    public Booking() { }

    public Booking(String seats, Double totalPrice, Integer nrChildTickets, Integer nrStudentTickets, Integer nrAdultTickets, Integer nrRetiredTickets, String customerEmail, String customerFirstName, String customerLastName, Showtime showtime, User user) {
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.nrChildTickets = nrChildTickets;
        this.nrStudentTickets = nrStudentTickets;
        this.nrAdultTickets = nrAdultTickets;
        this.nrRetiredTickets = nrRetiredTickets;
        this.customerEmail = customerEmail;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.showtime = showtime;
        this.user = user;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNrChildTickets() {
        return nrChildTickets;
    }

    public void setNrChildTickets(Integer nrChildTickets) {
        this.nrChildTickets = nrChildTickets;
    }

    public Integer getNrStudentTickets() {
        return nrStudentTickets;
    }

    public void setNrStudentTickets(Integer nrStudentTickets) {
        this.nrStudentTickets = nrStudentTickets;
    }

    public Integer getNrAdultTickets() {
        return nrAdultTickets;
    }

    public void setNrAdultTickets(Integer nrAdultTickets) {
        this.nrAdultTickets = nrAdultTickets;
    }

    public Integer getNrRetiredTickets() {
        return nrRetiredTickets;
    }

    public void setNrRetiredTickets(Integer nrRetiredTickets) {
        this.nrRetiredTickets = nrRetiredTickets;
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setBooking(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setBooking(null);
    }

    @Override
    public String toString() {
        if(user!=null) {
            return "Booking: " +
                    "ID=" + ID + " | " +
                    "showtime=" + showtime.getID() + " | " +
                    "user=" + user.getID() + " | " +
                    "customerEmail=" + customerEmail + " | " +
                    "customerFirstName=" + customerFirstName + " | " +
                    "customerLastName=" + customerLastName + " | " +
                    "seats=" + seats + " | " +
                    "nrChildTickets=" + nrChildTickets + " | " +
                    "nrStudentTickets=" + nrStudentTickets + " | " +
                    "nrAdultTickets=" + nrAdultTickets + " | " +
                    "nrRetiredTickets=" + nrRetiredTickets + " | " +
                    "totalPrice=" + totalPrice;
        }
        return "Booking: " +
                "ID=" + ID + " | " +
                "showtime=" + showtime.getID() + " | " +
                "user=" + user + " | " +
                "customerEmail=" + customerEmail + " | " +
                "customerFirstName=" + customerFirstName + " | " +
                "customerLastName=" + customerLastName + " | " +
                "seats=" + seats + " | " +
                "nrChildTickets=" + nrChildTickets + " | " +
                "nrStudentTickets=" + nrStudentTickets + " | " +
                "nrAdultTickets=" + nrAdultTickets + " | " +
                "nrRetiredTickets=" + nrRetiredTickets + " | " +
                "totalPrice=" + totalPrice;
    }
}
