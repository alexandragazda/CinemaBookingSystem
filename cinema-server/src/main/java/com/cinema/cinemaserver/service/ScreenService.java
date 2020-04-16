package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Screen;

import java.util.List;

public interface ScreenService {
    Screen findByID(Integer ID);

    Screen save(Screen screen);

    List<Screen> findAll();
}
