package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.utils.Email;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.UserRepository;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.EmailUtils;
import com.cinema.cinemaserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PlacedOrderService placedOrderService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Validator<User> validator;

    @Override
    public User save(User user) {
        validator.validate(user); //validates the given user

        if(findByEmail(user.getID())!=null) throw new ServiceException("The email already exists!"); //checks if the email exists

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName("ROLE_USER"));

        userRepository.save(user);

        return user;
    }

    @Override
    public User findByEmail(String email) {
        if(userRepository.findById(email).isPresent()) return userRepository.findById(email).get();
        return null;
    }

    @Override
    public User checkCredentials(User user) {
        User foundUser=findByEmail(user.getID());

        if(foundUser==null) return null; //there is no user with the given email

        if(bCryptPasswordEncoder.matches(user.getPassword(),foundUser.getPassword()))
            return foundUser; //the user credentials are valid

        return null; //the passwords do not match
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User resetPassword(String email, String oldPassword, String newPassword) {
        User foundUser=findByEmail(email);

        if(foundUser==null) return null; //there is no user with the given email

        if(!bCryptPasswordEncoder.matches(oldPassword,foundUser.getPassword())) return null; //the passwords do not match

        if(newPassword.length()<6) throw new ValidationException("Your new password is invalid!");

        foundUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(foundUser); //save() method can be used to add a new entry into your database as well as updating an existing one

        return findByEmail(email);
    }

    @Override
    public User solveForgotPassword(String username) {
        if(findByEmail(username) == null ) throw new ServiceException("Your email is invalid!");

        String newPassword=UserUtils.generateRandomPassword(); //generates a random password
        String subject= "Reset your password";
        String[] parts=username.split("@");
        String message= "Hello " + parts[0] + ",\nThis is your new password: " + newPassword;
        Email email= new Email(username,subject,message);
        EmailUtils.sendMail(email); //send an email with the new password

        User foundUser=findByEmail(username);
        foundUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(foundUser); //updates the password in the DB

        return findByEmail(username);
    }

    @Override
    public User update(String email, String newFirstName, String newLastName, String newPhoneNumber) {
        User foundUser=findByEmail(email);

        if(foundUser==null) return null; //there is no user with the given email

        foundUser.setFirstName(newFirstName);
        foundUser.setLastName(newLastName);
        foundUser.setPhoneNumber(newPhoneNumber);

        validator.validate(foundUser);

        userRepository.save(foundUser);

        return findByEmail(email);
    }

    @Override
    public void delete(String email) {
        if(findByEmail(email) == null) throw new ServiceException("Cannot find the user with the specified email!");

        //set the user for the bookings to null
        bookingService.findAll().forEach(x->{
            if(x.getUser()!=null && x.getUser().getID().equals(email)){
                x.setUser(null);
            }
        });

        //set the user for the orders to null
        placedOrderService.findAll().forEach(x->{
            if(x.getUser()!=null && x.getUser().getID().equals(email)){
                x.setUser(null);
            }
        });

        userRepository.deleteById(email);
    }
}
