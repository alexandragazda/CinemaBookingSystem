package com.cinema.cinemaserver.utils;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.utils.Email;
import com.cinema.cinemaserver.service.BookingService;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingUtils {
    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private BookingService bookingService;

    public List<List<Integer>> stateOfSeats(Integer showtimeID) {
        List<List<Integer>> stateOfSeats=new ArrayList<>();

        Showtime showtime=showtimeService.findById(showtimeID);
        Screen screen=showtime.getScreen();

        for(int i=0;i<screen.getNrRows();i++){
            List<Integer> cols=new ArrayList<>();
            for(int j=0;j<screen.getNrCols();j++){
                cols.add(0);
            }
            stateOfSeats.add(cols);
        }

        List<Booking> bookings=bookingService.findAllByShowtimeID(showtimeID);
        bookings.forEach(x->{
            String seats = x.getSeats();
            String[] rowscols = seats.split(";");
            for (int i = 0; i < rowscols.length; i++) {
                String[] rowscolsParsed = rowscols[i].split(":");
                int row = Integer.parseInt(rowscolsParsed[0]);
                String[] colsArray = rowscolsParsed[1].split(",");

                for (int l = 0; l < colsArray.length; l++) stateOfSeats.get(row).set(Integer.parseInt(colsArray[l]),1);
            }
        });

        return stateOfSeats;
    }

    public Integer getNrAvailableSeats(Integer showtimeID){
        List<List<Integer>> stateOfSeats= stateOfSeats(showtimeID);

        Integer nrAvailableTickets=0;
        for(int i=0;i<stateOfSeats.size();i++){
            for(int j=0;j<stateOfSeats.get(0).size();j++){
                if(stateOfSeats.get(i).get(j)==0) nrAvailableTickets++;
            }
        }

        return nrAvailableTickets;
    }

    public void sendBookingEmail(Integer bookingID){
        Booking booking=bookingService.findByID(bookingID);
        Showtime showtime=booking.getShowtime();

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
        message+="Total price: " + booking.getTotalPrice() + " RON\n\n";

        message+="Booking code: " + booking.getID() + "\n\n";

        message+="Please be there 15 minutes beforehand!\n\n";

        message+="Make sure your favourite movie treats are ready when your are!\nYou can order food and drinks online and we'll have them ready by the time you get here.\n\n";

        message+="Have a nice day!:)";

        Email email = new Email(booking.getCustomerEmail(), subject, message);
        EmailUtils.sendMail(email);
    }
}
