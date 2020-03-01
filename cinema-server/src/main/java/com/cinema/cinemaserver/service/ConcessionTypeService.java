package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.ConcessionType;

import java.util.List;

public interface ConcessionTypeService {
    List<ConcessionType> findAll();

    ConcessionType save(ConcessionType concessionType);

    ConcessionType findByID(String ID);
}
