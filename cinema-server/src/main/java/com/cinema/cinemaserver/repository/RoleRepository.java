package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> { }
