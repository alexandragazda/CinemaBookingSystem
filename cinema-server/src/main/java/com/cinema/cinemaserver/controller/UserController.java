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
import io.jsonwebtoken.MalformedJwtException;
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

    @GetMapping("/users") //vine sters
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/roles") //vine sters
    public List<Role> roles() {
        return roleService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User myUser) {
        Gson gson = new Gson();

        User user = userService.checkCredentials(myUser);

        if (user == null) return ResponseEntity.status(401).body(gson.toJson("", String.class));

        String token = UserUtils.createJWT(user, user.getRole().getID());

        return ResponseEntity.accepted().body(gson.toJson(token, String.class));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User myUser) {
        Gson gson = new Gson();

        try {
            userService.save(myUser);
            String token = UserUtils.createJWT(myUser, myUser.getRole().getID());
            return ResponseEntity.accepted().body(gson.toJson(token, String.class));
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


    @PutMapping("/user")
    public ResponseEntity<String> update(@RequestBody ObjectNode objectNode,
                                         @RequestHeader(value = "Authorization") String authorizationHeader) {
        Gson gson=new Gson();

        String token=authorizationHeader.substring(7); //we have bearer before token
        Claims decoded=UserUtils.decodeJWT(token);

        String newFirstName = objectNode.get("newFirstName").asText();
        String newLastName = objectNode.get("newLastName").asText();
        String newPhoneNumber = objectNode.get("newPhoneNumber").asText();
        try {
            User returnedUser = userService.update(decoded.getSubject(), newFirstName, newLastName, newPhoneNumber);
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

    @GetMapping("/user")
    public ResponseEntity<User> findById(@RequestHeader(value = "Authorization") String authorizationHeader){
        String token=authorizationHeader.substring(7); //we have bearer before token

        try {
            Claims decoded = UserUtils.decodeJWT(token);

            User user = userService.findByEmail(decoded.getSubject());
            return ResponseEntity.ok().body(user);
        } catch (MalformedJwtException ex){
            return null;
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity delete(@RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        try {
            userService.delete(decoded.getSubject());
            return ResponseEntity.status(200).build();
        }
        catch (ServiceException ex){
            return ResponseEntity.status(400).build(); //wrong email
        }
    }
}
