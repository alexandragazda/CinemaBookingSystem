package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Integer> { }
