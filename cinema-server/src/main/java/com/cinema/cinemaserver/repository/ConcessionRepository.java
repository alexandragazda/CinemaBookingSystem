package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Concession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessionRepository extends JpaRepository<Concession, Integer> { }
