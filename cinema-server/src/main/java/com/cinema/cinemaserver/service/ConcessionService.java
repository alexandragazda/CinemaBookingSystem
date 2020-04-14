package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Concession;

import java.util.List;

public interface ConcessionService {
    List<Concession> findAll();

    Concession save(Concession concession);

    Concession findByID(Integer ID);
}

