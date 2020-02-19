package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class BookingDTOValidator implements Validator<BookingDTO> {

    @Override
    public void validate(BookingDTO entity) {
        String msg="";

        if(entity.getShowtimeID() == null) msg+="ShowtimeID cannot be empty!";
        if(entity.getUserEmail()!=null && !EmailValidator.getInstance().isValid(entity.getUserEmail())) msg+="User email is invalid!";
        if(entity.getUserEmail() == null){
            if(entity.getCustomerEmail() == null) msg +="Customer email has to be specified if user email is null!";
            if(entity.getCustomerEmail() != null && !EmailValidator.getInstance().isValid(entity.getCustomerEmail())) msg+="Customer email is invalid!";
            if(entity.getCustomerFirstName() == null) msg+="Customer first name has to be specified if user email is null!";
            if(entity.getCustomerLastName() == null) msg+="Customer last name has to be specified if user email is null!";
        }
        if(entity.getNrChildTickets() == null) msg+="The number of child tickets has to be specified!";
        if(entity.getNrStudentTickets() == null) msg+="The number of student tickets has to be specified!";
        if(entity.getNrAdultTickets() == null) msg+="The number of adult tickets has to be specified!";
        if(entity.getNrRetiredTickets() == null) msg+="The number of retired tickets has to be specified!";
        if(entity.getNrChildTickets() == 0 && entity.getNrStudentTickets()==0 && entity.getNrAdultTickets()==0 && entity.getNrRetiredTickets()==0)
            msg+="No seats have been selected!";
        if(entity.getTotalPrice() == null) msg+="The total price has to be specified!";
        if(entity.getSelectedSeats() == null) msg+="Selected seats can't be empty!";

        if (msg != "") {
            throw new ValidationException(msg);
        }
    }
}
