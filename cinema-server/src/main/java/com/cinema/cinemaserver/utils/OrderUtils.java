package com.cinema.cinemaserver.utils;

import com.cinema.cinemaserver.domain.PlacedOrder;
import com.cinema.cinemaserver.domain.PlacedOrderItem;
import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.utils.Email;
import com.cinema.cinemaserver.service.PlacedOrderItemService;
import com.cinema.cinemaserver.service.PlacedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class OrderUtils {

    @Autowired
    private PlacedOrderService placedOrderService;

    @Autowired
    private PlacedOrderItemService placedOrderItemService;

    public void sendOrderEmail(Integer placedOrderID){
        PlacedOrder placedOrder=placedOrderService.findByID(placedOrderID);
        Showtime showtime=placedOrder.getShowtime();

        DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat=DateTimeFormatter.ofPattern("HH:mm");

        String subject="Order details";

        String message="";
        message+="Hello!\n\n" + "THANK YOU FOR YOUR ORDER!\n\n";

        message+="These are your order details:\n\n";
        message+="Movie: " + showtime.getMovie().getTitle() + "," + showtime.getTechnology().toString().split("_")[1] + "\n";
        message+="Date: " + dateFormat.format(showtime.getDate()) + "   " + "Time: " + timeFormat.format(showtime.getTime()) + "\n";
        message+="Screen: " + showtime.getScreen().getID() + "\n";
        message+="Age rating: " + showtime.getMovie().getAgeRating() + "\n\n";

        message+="Movie treats:\n";
        for (PlacedOrderItem i: placedOrderItemService.findAllByPlacedOrderID(placedOrderID)
             ) {
            message+=i.getConcession().getName() + " (" + i.getConcession().getPrice() + " RON)" + " x " + i.getQuantity() + "\n";
        }
        message+="Total price: " + placedOrder.getTotalPrice() + " RON\n";
        message+="Pick up time: " + timeFormat.format(placedOrder.getPickUpTime()) + "\n\n";

        message+="Order code: " + placedOrder.getID();

        message+="\n\nPlease be there at " + timeFormat.format(placedOrder.getPickUpTime()) + "!\n\nHave a nice day!:)";

        Email email= new Email(placedOrder.getCustomerEmail(),subject,message);
        EmailUtils.sendMail(email);
    }
}
