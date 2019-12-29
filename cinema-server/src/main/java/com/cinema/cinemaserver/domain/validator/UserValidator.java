package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.User;
import org.apache.commons.validator.routines.EmailValidator;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) {
        String msg="";

        if(!EmailValidator.getInstance().isValid(entity.getID())) msg+="Email is invalid!";
        if(entity.getPassword().length()<6) msg+="Password must contain at least 6 characters!";
        if(entity.getFirstName().equals("")) msg+="First name cannot be empty!";
        if(entity.getLastName().equals("")) msg+="Last name cannot be empty!";

        String phoneNumber=entity.getPhoneNumber();
        String regex="\\d+";
        if(!phoneNumber.equals("") && (phoneNumber.length()!=10 || !phoneNumber.matches(regex))) msg+="Phone number is invalid!";

        if (msg != "") {
            throw new ValidationException(msg);
        }
    }
}
