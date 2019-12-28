package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Role;
import com.cinema.cinemaserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        if(roleRepository.findById(name).isPresent()) return roleRepository.findById(name).get();
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete() {
        roleRepository.deleteById("ROLE_ADMIN");
        roleRepository.deleteById("ROLE_USER");
    }
}
