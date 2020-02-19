package com.cinema.cinemaserver.utils;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.Email;
import com.cinema.cinemaserver.domain.Showtime;

import java.time.format.DateTimeFormatter;

public class BookingUtils {

    public static void sendBookingEmail(Booking booking, Showtime showtime){
        DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat=DateTimeFormatter.ofPattern("HH:mm");

        String selectedSeatsInfo="";
        String[] selectedSeatsDTO=booking.getSeats().split(";");
        for(int i=0;i<selectedSeatsDTO.length;i++){
            String[] rowcols=selectedSeatsDTO[i].split(":");
            int row= Integer.parseInt(rowcols[0]) +1;
            selectedSeatsInfo+="Row: " + row + " Seat: ";
            String[] cols=rowcols[1].split(",");
            for(int j=0;j<cols.length;j++){
                int col=Integer.parseInt(cols[j]) + 1;
                selectedSeatsInfo+=col+",";
            }
            selectedSeatsInfo = selectedSeatsInfo.substring(0,selectedSeatsInfo.length() -1);
            selectedSeatsInfo+="\n";
        }

        String ticketsInfo="";
        if(booking.getNrChildTickets() > 0 ) ticketsInfo+=booking.getNrChildTickets() + " x " + "Child,   ";
        if(booking.getNrStudentTickets() > 0) ticketsInfo+=booking.getNrStudentTickets() + " x " + "Student,   ";
        if(booking.getNrAdultTickets() > 0 ) ticketsInfo+=booking.getNrAdultTickets()+ " x " + "Adult,   ";
        if(booking.getNrRetiredTickets() > 0) ticketsInfo+=booking.getNrRetiredTickets() + " x " + "Retired,   ";
        ticketsInfo = ticketsInfo.substring(0, ticketsInfo.length() - 4);

        String subject= "Booking details";
        String message="";
        message+= "Hello!\n\n" + "THANK YOU FOR YOUR BOOKING!\n\n";
        message+="These are your booking details:\n\n";
        message+="Movie: " + showtime.getMovie().getTitle() + "," + showtime.getTechnology().toString().split("_")[1] + "\n";
        message+="Date: " + dateFormat.format(showtime.getDate()) + "   " + "Time: " + timeFormat.format(showtime.getTime()) + "\n";
        message+="Screen: " + showtime.getScreen().getID() + "\n";
        message+="Age rating: " + showtime.getMovie().getAgeRating() + "\n\n";
        message+="Tickets: " + ticketsInfo + "\n";
        message+="Seats:\n" + selectedSeatsInfo;
        message+="Total price: " + booking.getTotalPrice();
        message+="\n\nPlease be there 15 minutes beforehand!\n\nHave a nice day!:)";
        Email email= new Email(booking.getCustomerEmail(),subject,message);
        EmailUtils.sendMail(email);

    }
}
