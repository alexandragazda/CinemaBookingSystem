package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Role;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.service.RoleService;
//import com.cinema.cinemaserver.service.SecurityService;
import com.cinema.cinemaserver.service.UserService;
import com.cinema.cinemaserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/")
    public String welcome() {
        //roleRepository.save(new Role("ROLE_USER"));
        //roleRepository.save(new Role("ROLE_ADMIN"));
        //userService.delete();
        //userService.save(new User("alexandragazda@yahoo.com","aleoscar25"));
        //userService.save(new User("terezamustea@yahoo.com","tereza"));
        //userService.save(new User("georgetarta@gmail.com","admin55"));


        //roleRepository.save(new Role("role"));
        //userService.save(new User("a","a"));
        //roleRepository.deleteById("role");

//        Role role = new Role("role");
//        User user=new User("a","a");
//        role.addUser(user);
//        roleService.save(role);
          //System.out.println(roleService.findByName("ROLE_USER").getUsers().size());

//        User user=new User("a","a");
//        user.setRole(roleRepository.findByName("ROLE_USER"));
//        userService.save(user);

        //userService.delete();

//        roleService.save(new Role("role"));
        //roleService.delete();
//        System.out.println(roleService.findByName("ROLE_USER").getID());

        return "welcome";
    }

    @GetMapping("/users")
    public List<User> users(){
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> roles(){
        return roleService.findAll();
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User myUser){

        User user=userService.checkCredentials(myUser);

        if(user==null) return ResponseEntity.status(401).body(new User());

        String token=userUtils.getJWTToken(user.getID());
        user.setToken(token);

        //System.out.println(securityService.findLoggedInUsername());
        //securityService.autoLogin(user.getID(),user.getPassword());
        //System.out.println(securityService.findLoggedInUsername());
        return ResponseEntity.accepted().body(user);
        //securityService.autoLogin(myUser.getID(),myUser.getPassword());
        //System.out.println(securityService.findLoggedInUsername());
        //return null;
    }
}
