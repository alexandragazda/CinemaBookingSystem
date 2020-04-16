package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.PlacedOrderItem;
import org.springframework.stereotype.Component;

@Component
public class PlacedOrderItemValidator implements Validator<PlacedOrderItem> {
    @Override
    public void validate(PlacedOrderItem entity) {
        String msg="";

        if(entity.getQuantity() <= 0) msg+="Quantity has to be > 0!";
        if(entity.getPlacedOrder() == null) msg+="Placed order has to be specified!";
        if(entity.getConcession() == null) msg+="Concession has to be specified!";

        if (!msg.equals("")) {
            throw new ValidationException(msg);
        }
    }
}
