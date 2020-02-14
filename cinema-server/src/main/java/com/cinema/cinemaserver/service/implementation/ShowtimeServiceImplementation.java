package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.repository.ShowtimeRepository;
import com.cinema.cinemaserver.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Showtime> findAllByMovieIdAndDate(Integer movieId, LocalDate date) {
        //if date = today => return only the showtimes which are after the current time
        if(date.isEqual(LocalDate.of(2020,1,17))) { //!!!!!!! today
            //return the showtimes sorted by time
            return findAllTodayByMovieIdAndCurrentTIme(movieId)
                    .stream()
                    .sorted(Comparator.comparing(Showtime::getTime))
                    .collect(Collectors.toList());
        }

        //return the showtimes sorted by time
        return showtimeRepository.findAllByMovieIdAndDate(movieId,date)
                .stream()
                .sorted(Comparator.comparing(Showtime::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> findAllTodayByMovieIdAndCurrentTIme(Integer movieId){
        return showtimeRepository.findAllTodayByMovieIdAndCurrentTime(movieId);
    }
    @Override
    public void delete() {
        showtimeRepository.deleteById(25);
    }

}
