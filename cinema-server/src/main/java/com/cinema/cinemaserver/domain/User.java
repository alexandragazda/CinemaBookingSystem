package com.cinema.cinemaserver.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class User implements HasID<String> {

    private static final long serialVersionUID = 2671271848840956581L;

    @Id
    @Column(name="email")
    private String username;

    private String password;

    @ColumnDefault("-1")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "role_name")
    private Role role;

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getID() {
        return username;
    }

    public void setID(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "User: "
                + "email=" +  username + " | "
                + "password=" + password + " | "
                + "token=" + token + " | "
                + "role=" + role;
    }
}
