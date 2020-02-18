package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import org.springframework.stereotype.Component;

@Component
public class BookingDTOValidator implements Validator<BookingDTO> {

    @Override
    public void validate(BookingDTO entity) {
        String msg="";

        if(entity.getShowtimeID() == null) msg+="ShowtimeID cannot be empty!";
        if(entity.getNrChildTickets() == null) msg+="The number of child tickets has to be specified!";
        if(entity.getNrStudentTickets() == null) msg+="The number of student tickets has to be specified!";
        if(entity.getNrAdultTickets() == null) msg+="The number of adults tickets has to be specified!";
        if(entity.getNrRetiredTickets() == null) msg+="The number of retired tickets has to be specified!";
        if(entity.getTotalPrice() == null) msg+="The total price has to be specified!";
        if(entity.getSelectedSeats() == null) msg+="Selected seats connot be empty";

        if (msg != "") {
            throw new ValidationException(msg);
        }
    }
}
