package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.repository.ShowtimeRepository;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowtimeServiceImplementation implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Showtime findById(Integer id) {
        if(showtimeRepository.findById(id).isPresent()) return showtimeRepository.findById(id).get();
        return null;
    }

    @Override
    public Showtime save(Showtime showtime) {
        showtimeRepository.save(showtime);

        return showtime;
    }

    @Override
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public List<Showtime> findAllByMovieId(Integer movieId) {
        return showtimeRepository.findAllByMovieId(movieId);
    }


}
