package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Role;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.repository.RoleRepository;
import com.cinema.cinemaserver.service.UserService;
import com.cinema.cinemaserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/")
    public String welcome(){
//        roleRepository.save(new Role("ROLE_USER"));
//        roleRepository.save(new Role("ROLE_ADMIN"));

       // userService.delete();
       //userService.save(new User("alexandragazda@yahoo.com","aleoscar25"));

        return "welcome";
    }

    @GetMapping("/users")
    public List<User> users(){
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User myUser){
        User user=userService.checkCredentials(myUser);

        if(user==null) return ResponseEntity.status(401).body(new User());

        String token=userUtils.getJWTToken(user.getID());
        user.setToken(token);

        return ResponseEntity.accepted().body(user);
    }
}
