package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    Role findByName(String name);

    List<Role> findAll();

    void delete();
}
