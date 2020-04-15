package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements HasID<String>{

    private static final long serialVersionUID = 3221271848840900581L;

    @Id
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<User> users = new HashSet<>();

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRole(null);
    }


    public String getID() {
        return name;
    }

    public void setID(String name) {
        this.name=name;
    }


    @Override
    public String toString() {
        return "Role: "
                + "name="+name;
    }
}
