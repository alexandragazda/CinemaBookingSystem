package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.utils.OrderItem;

import java.time.LocalTime;
import java.util.List;

public class OrderDTO {
    private Integer showtimeID;
    private String userEmail;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private List<OrderItem> orderItems;
    private Double totalPrice;
    private LocalTime pickUpTime;

    public OrderDTO() { }

    public OrderDTO(Integer showtimeID, String userEmail, String customerEmail, String customerFirstName, String customerLastName, List<OrderItem> orderItems, Double totalPrice, LocalTime pickUpTime) {
        this.showtimeID = showtimeID;
        this.userEmail = userEmail;
        this.customerEmail = customerEmail;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.pickUpTime = pickUpTime;
    }

    public Integer getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(Integer showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override
    public String toString() {
        return "OrderDTO: " +
                "showtimeID=" + showtimeID + " | " +
                "userEmail=" + userEmail + " | " +
                "customerEmail=" + customerEmail + " | " +
                "customerFirstName=" + customerFirstName + " | " +
                "customerLastName=" + customerLastName + " | " +
                "totalPrice=" + totalPrice + " | " +
                "nrOrderItems=" + orderItems.size() + " | " +
                "pickUpTime=" + pickUpTime;
    }
}

