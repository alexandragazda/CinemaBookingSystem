package com.cinema.cinemaserver.utils;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.dtos.*;
import com.cinema.cinemaserver.service.PlacedOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converters {
    @Autowired
    private PlacedOrderItemService placedOrderItemService;

    public Booking convertFromBookingDTOToBookingWithUser(BookingDTO bookingDTO, User user, Showtime showtime){
        Booking booking=new Booking();

        booking.setSeats(bookingDTO.getSelectedSeats());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setNrChildTickets(bookingDTO.getNrChildTickets());
        booking.setNrStudentTickets(bookingDTO.getNrStudentTickets());
        booking.setNrAdultTickets(bookingDTO.getNrAdultTickets());
        booking.setNrRetiredTickets(bookingDTO.getNrRetiredTickets());
        booking.setCustomerEmail(user.getID());
        booking.setCustomerFirstName(user.getFirstName());
        booking.setCustomerLastName(user.getLastName());
        booking.setShowtime(showtime);
        booking.setUser(user);

        return booking;
    }

    public Booking convertFromBookingDTOToBookingWithCustomer(BookingDTO bookingDTO, Showtime showtime){
        Booking booking=new Booking();

        booking.setSeats(bookingDTO.getSelectedSeats());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setNrChildTickets(bookingDTO.getNrChildTickets());
        booking.setNrStudentTickets(bookingDTO.getNrStudentTickets());
        booking.setNrAdultTickets(bookingDTO.getNrAdultTickets());
        booking.setNrRetiredTickets(bookingDTO.getNrRetiredTickets());
        booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        booking.setCustomerFirstName(bookingDTO.getCustomerFirstName());
        booking.setCustomerLastName(bookingDTO.getCustomerLastName());
        booking.setShowtime(showtime);
        booking.setUser(null);

        return booking;
    }

    public PlacedOrder convertFromOrderDTOToOrderWithUser(OrderDTO orderDTO, User user, Showtime showtime){
        PlacedOrder placedOrder=new PlacedOrder();

        placedOrder.setTotalPrice(orderDTO.getTotalPrice());
        placedOrder.setPickUpTime(orderDTO.getPickUpTime());
        placedOrder.setCustomerEmail(user.getID());
        placedOrder.setCustomerFirstName(user.getFirstName());
        placedOrder.setCustomerLastName(user.getLastName());
        placedOrder.setUser(user);
        placedOrder.setShowtime(showtime);

        return placedOrder;
    }

    public PlacedOrder convertFromOrderDTOToOrderWithCustomer(OrderDTO orderDTO, Showtime showtime){
        PlacedOrder placedOrder=new PlacedOrder();

        placedOrder.setTotalPrice(orderDTO.getTotalPrice());
        placedOrder.setPickUpTime(orderDTO.getPickUpTime());
        placedOrder.setCustomerEmail(orderDTO.getCustomerEmail());
        placedOrder.setCustomerFirstName(orderDTO.getCustomerFirstName());
        placedOrder.setCustomerLastName(orderDTO.getCustomerLastName());
        placedOrder.setUser(null);
        placedOrder.setShowtime(showtime);

        return placedOrder;
    }

    public List<BookingInfoDTO> convertFromBookingsToBookingInfoDTOS(List<Booking> bookings){
        List<BookingInfoDTO> bookingInfoDTOS=new ArrayList<>();
        bookings.forEach(x->{
            BookingInfoDTO bookingInfoDTO=convertFromBookingToBookingInfoDTO(x);
            bookingInfoDTOS.add(bookingInfoDTO);
        });

        return bookingInfoDTOS;
    }

    public BookingInfoDTO convertFromBookingToBookingInfoDTO(Booking booking){
        BookingInfoDTO bookingInfoDTO=new BookingInfoDTO();

        bookingInfoDTO.setBookingID(booking.getID());
        bookingInfoDTO.setNrChildTickets(booking.getNrChildTickets());
        bookingInfoDTO.setNrStudentTickets(booking.getNrStudentTickets());
        bookingInfoDTO.setNrAdultTickets(booking.getNrAdultTickets());
        bookingInfoDTO.setNrRetiredTickets(booking.getNrRetiredTickets());
        bookingInfoDTO.setTotalPrice(booking.getTotalPrice());
        bookingInfoDTO.setSeats(booking.getSeats());
        bookingInfoDTO.setMovieTitle(booking.getShowtime().getMovie().getTitle());
        bookingInfoDTO.setMovieTechnology(booking.getShowtime().getTechnology());
        bookingInfoDTO.setShowtimeDate(booking.getShowtime().getDate());
        bookingInfoDTO.setShowtimeTime(booking.getShowtime().getTime());
        bookingInfoDTO.setScreenID(booking.getShowtime().getScreen().getID());

        return bookingInfoDTO;
    }

    public List<OrderInfoDTO> convertFromOrdersToOrderInfoDTOS(List<PlacedOrder> orders){
        List<OrderInfoDTO> orderInfoDTOS=new ArrayList<>();
        orders.forEach(x-> {
            List<PlacedOrderItem> placedOrderItems = placedOrderItemService.findAllByPlacedOrderID(x.getID());
            List<PlacedOrderItemDTO> placedOrderItemDTOS = new ArrayList<>();
            placedOrderItems.forEach(y -> {
                PlacedOrderItemDTO placedOrderItemDTO = new PlacedOrderItemDTO(y.getQuantity(), y.getConcession().getName(), y.getConcession().getPrice());
                placedOrderItemDTOS.add(placedOrderItemDTO);
            });
            OrderInfoDTO orderInfoDTO = convertFromOrderToOrderInfoDTO(x,placedOrderItemDTOS);
            orderInfoDTOS.add(orderInfoDTO);
        });

        return orderInfoDTOS;
    }

    public OrderInfoDTO convertFromOrderToOrderInfoDTO(PlacedOrder placedOrder, List<PlacedOrderItemDTO> placedOrderItemDTOS){
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();

        orderInfoDTO.setOrderID(placedOrder.getID());
        orderInfoDTO.setPlacedOrderItemDTOS(placedOrderItemDTOS);
        orderInfoDTO.setTotalPrice(placedOrder.getTotalPrice());
        orderInfoDTO.setPickUpTime(placedOrder.getPickUpTime());
        orderInfoDTO.setMovieTitle(placedOrder.getShowtime().getMovie().getTitle());
        orderInfoDTO.setMovieTechnology(placedOrder.getShowtime().getTechnology());
        orderInfoDTO.setShowtimeDate(placedOrder.getShowtime().getDate());
        orderInfoDTO.setShowtimeTime(placedOrder.getShowtime().getTime());
        orderInfoDTO.setScreenID(placedOrder.getShowtime().getScreen().getID());

        return orderInfoDTO;
    }

    public MovieDTO convertFromMovieToMovieDTO(Movie movie, LocalDate firstDate){
        MovieDTO movieDTO=new MovieDTO();

        movieDTO.setMovieID(movie.getID());
        movieDTO.setMoviePoster(movie.getPoster());
        movieDTO.setMovieTitle(movie.getTitle());
        movieDTO.setMovieLinkIMDb(movie.getLinkIMDb());
        movieDTO.setFirstDate(firstDate);

        return movieDTO;
    }
}
