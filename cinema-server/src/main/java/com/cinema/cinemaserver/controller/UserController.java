package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Role;
import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.RoleService;
import com.cinema.cinemaserver.service.ServiceException;
import com.cinema.cinemaserver.service.UserService;
import com.cinema.cinemaserver.utils.UserUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    @GetMapping("/")
//    public String welcome() {
////        System.out.println(roleService.findByName("ROLE_USER").getUsers().size());
////        System.out.println(roleService.findByName("ROLE_ADMIN").getUsers().size());
//
//        roleService.save(new Role("ROLE_USER"));
//        roleService.save(new Role("ROLE_ADMIN"));
//        //userService.delete();
//        userService.save(new User("alexandragazda@yahoo.com","aleoscar25","Alexandra","Gazda","0729094605"));
//        try {
//            //userService.save(new User("terezamustea@yahoo.com","tereza23","Tereza","Mustea",""));
//            //userService.save(new User("tartageorge@gmail.com","admin55","Geroge","Tarta","0745892210"));
//            //userService.save(new User("test32@yahoo.com","test55","George","Tarta","0745892210"));
//
//            //System.out.println(userService.resetPassword("test11@gmail.com","test11","test17"));
//            //System.out.println(userService.resetPassword("t@yahoo.com","test13","test18"));
//        } catch (ValidationException | ServiceException ex) {
//            System.out.println(ex);
//        }
//        //userService.save(new User("terezamustea@yahoo.com","tereza"));
//        //userService.save(new User("georgetarta@gmail.com","admin55"));
//        //userService.save(new User("georgianat@gmail.com","georgi5"));
//
//        //roleRepository.save(new Role("role"));
//        //userService.save(new User("a","a"));
//        //roleRepository.deleteById("role");
//
////        Role role = new Role("role");
////        User user=new User("a","a");
////        role.addUser(user);
////        roleService.save(role);
//        //System.out.println(roleService.findByName("ROLE_USER").getUsers().size());
//
////        User user=new User("a","a");
////        user.setRole(roleRepository.findByName("ROLE_USER"));
////        userService.save(user);
//
//        //userService.delete();
//
//
////        roleService.save(new Role("role"));
//        //roleService.delete();
////        System.out.println(roleService.findByName("ROLE_USER").getID());
//
//        //roleService.delete();
//        //userService.delete();
//        return "welcome";
//    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> roles() {
        return roleService.findAll();
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User myUser) {

        Gson gson = new Gson();

        User user = userService.checkCredentials(myUser);

        if (user == null) return ResponseEntity.status(401).body(gson.toJson("", String.class));

        String token = UserUtils.createJWT(user.getID(), user.getRole().getID());

        return ResponseEntity.accepted().body(gson.toJson(token, String.class));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User myUser) {

        Gson gson = new Gson();

        try {
            userService.save(myUser);
            return ResponseEntity.accepted().body(gson.toJson("", String.class));
        } catch (ValidationException ex) {
            return ResponseEntity.status(422).body(gson.toJson(ex.getMessage(), String.class)); //validation error
        } catch (ServiceException ex) {
            return ResponseEntity.status(409).body(gson.toJson(ex.getMessage(), String.class)); //duplicate in DB
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ObjectNode objectNode,
                                                @RequestHeader(value = "Authorization") String authorizationHeader) {

        Gson gson = new Gson();

        String token=authorizationHeader.substring(7); //we have bearer before token
        Claims decoded=UserUtils.decodeJWT(token);
        String email=decoded.getSubject();

        String oldPassword = objectNode.get("oldPassword").asText();
        String newPassword = objectNode.get("newPassword").asText();

        try {
            User returnedUser = userService.resetPassword(email, oldPassword, newPassword);
            if (returnedUser == null)
                return ResponseEntity.status(401).body(gson.toJson("", String.class)); //the user provided wrong credentials

            return ResponseEntity.accepted().body(gson.toJson("", String.class));
        }
        catch (ValidationException ex){
            return ResponseEntity.status(422).body(gson.toJson(ex.getMessage(), String.class)); //validation error
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ObjectNode objectNode) {

        Gson gson = new Gson();

        String username=objectNode.get("email").asText();

        try{
            userService.solveForgotPassword(username);

            return ResponseEntity.accepted().body(gson.toJson("", String.class));
        }
        catch (ServiceException ex){
            return ResponseEntity.status(401).body(gson.toJson(ex.getMessage(), String.class));
        }

    }

    @PostMapping("/send")
    public ResponseEntity<Claims> token(
            @RequestHeader(value = "Authorization") String authorizationHeader
    ) {

        Gson gson = new Gson();

        String token=authorizationHeader.substring(7); //we have bearer before token

        Claims decoded=UserUtils.decodeJWT(token);

        System.out.println(decoded.getSubject());

        return ResponseEntity.accepted().body(decoded);
    }
}
