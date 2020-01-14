package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.Screen;

import java.util.List;

public interface ScreenService {
    Screen findById(Integer id);

    Screen save(Screen screen);

    List<Screen> findAll();

}
