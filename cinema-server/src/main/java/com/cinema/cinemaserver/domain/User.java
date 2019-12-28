package com.cinema.cinemaserver.domain;

import javax.persistence.*;

@Entity
public class User implements HasID<String> {

    private static final long serialVersionUID = 2671271848840956581L;

    @Id
    private String email;

    private String password;


    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "role_name")
    private Role role;

    public User() { }

    public User(String email, String password) {
        this.email=email;
        this.password = password;
    }

    public String getID() {
        return email;
    }

    public void setID(String email) {
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "User: "
                + "email=" +  email + " | "
                + "password=" + password + " | "
                + "role=" + role;
    }
}
