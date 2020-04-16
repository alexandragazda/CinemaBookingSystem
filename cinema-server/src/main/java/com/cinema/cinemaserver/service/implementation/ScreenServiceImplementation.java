package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Screen;
import com.cinema.cinemaserver.repository.ScreenRepository;
import com.cinema.cinemaserver.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImplementation implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Screen findByID(Integer ID) {
        if(screenRepository.findById(ID).isPresent()) return screenRepository.findById(ID).get();
        return null;
    }

    @Override
    public Screen save(Screen screen) {
        screenRepository.save(screen);

        return screen;
    }

    @Override
    public List<Screen> findAll() {
        return screenRepository.findAll();
    }
}
