package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.enums.Technology;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderInfoDTO {
    private Integer orderID;
    private List<PlacedOrderItemDTO> placedOrderItemDTOS;
    private Double totalPrice;
    private LocalTime pickupTime;
    private String movieTitle;
    private Technology movieTechnology;
    private LocalDate showtimeDate;
    private LocalTime showtimeTime;
    private Integer screenID;

    public OrderInfoDTO() { }

    public OrderInfoDTO(Integer orderID, List<PlacedOrderItemDTO> placedOrderItemDTOs, Double totalPrice, LocalTime pickupTime, String movieTitle, Technology movieTechnology, LocalDate showtimeDate, LocalTime showtimeTime, Integer screenID) {
        this.orderID = orderID;
        this.placedOrderItemDTOS = placedOrderItemDTOs;
        this.totalPrice = totalPrice;
        this.pickupTime = pickupTime;
        this.movieTitle = movieTitle;
        this.movieTechnology = movieTechnology;
        this.showtimeDate = showtimeDate;
        this.showtimeTime = showtimeTime;
        this.screenID = screenID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public List<PlacedOrderItemDTO> getPlacedOrderItemDTOS() {
        return placedOrderItemDTOS;
    }

    public void setPlacedOrderItemDTOS(List<PlacedOrderItemDTO> placedOrderItemDTOS) {
        this.placedOrderItemDTOS = placedOrderItemDTOS;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Technology getMovieTechnology() {
        return movieTechnology;
    }

    public void setMovieTechnology(Technology movieTechnology) {
        this.movieTechnology = movieTechnology;
    }

    public LocalDate getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(LocalDate showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public LocalTime getShowtimeTime() {
        return showtimeTime;
    }

    public void setShowtimeTime(LocalTime showtimeTime) {
        this.showtimeTime = showtimeTime;
    }

    public Integer getScreenID() {
        return screenID;
    }

    public void setScreenID(Integer screenID) {
        this.screenID = screenID;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO:" +
                "orderID=" + orderID + " | " +
                "totalPrice=" + totalPrice + " | " +
                "pickupTime=" + pickupTime + " | " +
                "movieTitle='" + movieTitle + " | " +
                "movieTechnology=" + movieTechnology + " | " +
                "showtimeDate=" + showtimeDate + " | " +
                "showtimeTime=" + showtimeTime + " | " +
                "screenID=" + screenID;
    }
}
