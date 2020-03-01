package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate //user represents a table that has many columns and only a few of these columns are required to be updated frequently (password)
public class User implements HasID<String> {

    private static final long serialVersionUID = 2671271848840956581L;

    @Id
    private String email;

    private String password;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "role_name")
    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@ID")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<Booking> bookingSet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<PlacedOrder> placedOrderSet = new HashSet<>();

    public User() { }

    public User(String email, String password) {
        this.email=email;
        this.password = password;
    }

    public User(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getID() {
        return email;
    }

    public void setID(String email) {
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void addBooking(Booking booking) {
        bookingSet.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking) {
        bookingSet.remove(booking);
        booking.setUser(null);
    }

    public Set<PlacedOrder> getPlacedOrderSet() {
        return placedOrderSet;
    }

    public void addPlacedOrder(PlacedOrder placedOrder) {
        placedOrderSet.add(placedOrder);
        placedOrder.setUser(this);
    }

    public void removePlacedOrder(PlacedOrder placedOrder) {
        placedOrderSet.remove(placedOrder);
        placedOrder.setUser(null);
    }

    @Override
    public String toString() {
        return "User: "
                + "email=" +  email + " | "
                + "password=" + password + " | "
                + "firstName=" +  firstName + " | "
                + "lastName=" + lastName + " | "
                + "phoneNumber=" + phoneNumber + " | "
                + "role=" + role.getID();
    }
}
