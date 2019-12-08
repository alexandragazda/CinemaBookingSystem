package com.cinema.cinemaserver.service;


import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.repository.RoleRepository;
import com.cinema.cinemaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User checkCredentials(User user) {
        User foundUser=userRepository.findByUsername(user.getID());

        if(foundUser==null) return null; //there is no user with the given username

        if(bCryptPasswordEncoder.matches(user.getPassword(),foundUser.getPassword()))
            return foundUser; //the user credentials are valid

        return null; //the password do not match
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

//    @Override
//    public void delete() {
//        userRepository.deleteById("alexandragazda@yahoo.com");
//    }
}
