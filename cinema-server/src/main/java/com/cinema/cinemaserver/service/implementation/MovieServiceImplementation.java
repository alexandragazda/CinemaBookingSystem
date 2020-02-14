package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.MovieRepository;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Validator<Movie> validator;

    @Override
    public Movie findById(Integer id) {
        if(movieRepository.findById(id).isPresent()) return movieRepository.findById(id).get();
        return null;
    }

    @Override
    public Movie findByTitle(String title){
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findAllByDate(LocalDate date){
        if(date.isEqual(LocalDate.of(2020,1,17))){ //!!!!!!!! today
            //return movies sorted by title
            return findAllTodayByCurrentTime()
                    .stream()
                    .sorted(Comparator.comparing(Movie::getTitle))
                    .collect(Collectors.toList());
        }
        //return movies sorted by title
        return movieRepository.findAllByDate(date)
                .stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findAllTodayByCurrentTime(){
        return movieRepository.findAllTodayByCurrentTime();
    }

    @Override
    public Movie save(Movie movie) {
        //validator.validate(movie); //validates the given movie

        movieRepository.save(movie);

        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void delete() {
        movieRepository.deleteById(22);
    }
}
