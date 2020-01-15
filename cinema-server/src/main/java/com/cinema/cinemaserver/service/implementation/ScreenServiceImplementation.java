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
    public Screen findById(Integer id) {
        if(screenRepository.findById(id).isPresent()) return screenRepository.findById(id).get();
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

    @Override
    public void delete() {
        screenRepository.deleteById(3);
    }
}
