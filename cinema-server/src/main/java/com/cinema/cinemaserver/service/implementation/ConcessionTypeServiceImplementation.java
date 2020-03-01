package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.ConcessionType;
import com.cinema.cinemaserver.repository.ConcessionTypeRepository;
import com.cinema.cinemaserver.service.ConcessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionTypeServiceImplementation implements ConcessionTypeService {
    @Autowired
    private ConcessionTypeRepository concessionTypeRepository;

    @Override
    public List<ConcessionType> findAll() {
        return concessionTypeRepository.findAll();
    }

    @Override
    public ConcessionType save(ConcessionType concessionType) {
        concessionTypeRepository.save(concessionType);

        return concessionType;
    }

    @Override
    public ConcessionType findByID(String ID) {
        if(concessionTypeRepository.findById(ID).isPresent()) return concessionTypeRepository.findById(ID).get();
        return null;
    }
}
