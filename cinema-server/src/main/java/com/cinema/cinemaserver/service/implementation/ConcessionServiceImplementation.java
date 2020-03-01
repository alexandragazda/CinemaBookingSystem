package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Concession;
import com.cinema.cinemaserver.repository.ConcessionRepository;
import com.cinema.cinemaserver.service.ConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionServiceImplementation implements ConcessionService {
    @Autowired
    private ConcessionRepository concessionRepository;

    @Override
    public List<Concession> findAll() {
        return concessionRepository.findAll();
    }

    @Override
    public Concession save(Concession concession) {
        concessionRepository.save(concession);

        return concession;
    }

    @Override
    public Concession findByID(Integer ID) {
        if(concessionRepository.findById(ID).isPresent()) return concessionRepository.findById(ID).get();
        return null;
    }

//    @Override
//    public List<Concession> findAllByConcessionType(String concessionType) {
//        return concessionRepository.findAllByConcessionType(concessionType);
//    }
}

