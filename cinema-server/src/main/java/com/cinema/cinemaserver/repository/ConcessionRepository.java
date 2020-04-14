package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Concession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcessionRepository extends JpaRepository<Concession, Integer> { }
