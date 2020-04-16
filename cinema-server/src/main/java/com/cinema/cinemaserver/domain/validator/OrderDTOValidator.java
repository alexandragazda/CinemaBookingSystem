package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOValidator implements Validator<OrderDTO> {
    @Override
    public void validate(OrderDTO entity) {
        String msg="";

        if(entity.getShowtimeID() == null) msg+="ShowtimeID cannot be empty!";
        if(entity.getUserEmail()!=null && !EmailValidator.getInstance().isValid(entity.getUserEmail())) msg+="User email is invalid!";
        if(entity.getUserEmail() == null){
            if(entity.getCustomerEmail() == null) msg +="Customer email has to be specified if user email is null!";
            if(entity.getCustomerEmail() != null && !EmailValidator.getInstance().isValid(entity.getCustomerEmail())) msg+="Customer email is invalid!";
            if(entity.getCustomerFirstName() == null) msg+="Customer first name has to be specified if user email is null!";
            if(entity.getCustomerLastName() == null) msg+="Customer last name has to be specified if user email is null!";
        }
        if(entity.getOrderItems() == null) msg+="Order item list cannot be empty!";
        if(entity.getTotalPrice() == 0) msg+="Total price cannot be 0!";
        if(entity.getPickUpTime() == null) msg+="Pick up time has to pe specified!";

        if (!msg.equals("")) {
            throw new ValidationException(msg);
        }
    }
}
