package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator implements Validator<Ticket> {

    @Override
    public void validate(Ticket entity) {
        String msg="";

        if(entity.getPrice() == null) msg+="Price has to be specified!";
        if(entity.getLine() == null) msg+="Line has to be specified!";
        if(entity.getCol() == null) msg+="Column has to be specified!";
        if(entity.getTicketType() == null) msg+="TicketType cannot be empty!";
        if(entity.getBooking() == null) msg+="Booking cannot be empty!";

        if (!msg.equals("")) {
            throw new ValidationException(msg);
        }
    }
}
