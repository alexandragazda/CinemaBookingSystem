package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByEmail(String email);

    User checkCredentials(User user);

    List<User> findAll();

    void delete();
}
