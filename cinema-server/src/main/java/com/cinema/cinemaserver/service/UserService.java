package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User checkCredentials(User user);

    List<User> findAll();

    void delete();
}
