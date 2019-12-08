package com.cinema.cinemaserver.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements HasID<String> {

    private static final long serialVersionUID = 3221271848840900581L;

    @Id
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Set<User> users) {
        this.name=name;

        this.users=new HashSet<>();
        this.users.addAll(users);
        this.users.forEach(x->x.setRole(this));
    }

    public String getID() {
        return name;
    }

    public void setID(String roleName) {
        this.name=name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role: "
                + "name="+name;
    }
}
