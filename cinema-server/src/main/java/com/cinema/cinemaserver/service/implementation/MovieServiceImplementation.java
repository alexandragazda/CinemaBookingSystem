package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.dtos.MovieDTO;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.MovieRepository;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ShowtimeService;
import com.cinema.cinemaserver.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Validator<Movie> validator;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private Converters converters;

    private static final LocalDate today=LocalDate.of(2020,3,19); // !!!!!!!!!! today

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
        if(date.isEqual(today)){
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
        if(LocalTime.now().isBefore(LocalTime.of(23,40)))
            return movieRepository.findAllTodayByCurrentTime(LocalTime.now().plusMinutes(20));

        return new ArrayList<>(); //for showtimes after 23:40 (you cannot make any bookings/orders for them)
    }

    @Override
    public List<MovieDTO> findAllByWatchlistID(String watchlistID) {
        List<Movie> movies=movieRepository.findAllByWatchlistID(watchlistID);
        List<MovieDTO> movieDTOS=new ArrayList<>();

        for (Movie m: movies
             ) {
            List<Showtime> movieShowtimes=showtimeService.findAllByMovieId(m.getID());
            if(movieShowtimes.size()!=0) {
                movieShowtimes
                    .stream()
                        .filter(x -> x.getDate().isAfter(today) || x.getDate().isEqual(today))
                        .sorted(Comparator.comparing(Showtime::getDate))
                        .collect(Collectors.toList());
                LocalDate firstDate = movieShowtimes.get(0).getDate();
                if (firstDate.isEqual(today)
                        && showtimeService.findAllTodayByMovieIdAndCurrentTime(m.getID()).size() == 0) {
                    int i = 0;
                    while (movieShowtimes.get(i).getDate().isEqual(today)) i++;
                    firstDate = movieShowtimes.get(i).getDate();
                }
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m, firstDate));
            }
            else{
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m, null));
            }
        }

        return movieDTOS
                .stream()
                .sorted(Comparator.comparing(MovieDTO::getMovieTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findAllByReleaseDate() {
        return movieRepository.findAllByReleaseDate();
    }

    @Override
    public List<Movie> findAllByReleaseMonth(String month) {
        return movieRepository.findAllByReleaseMonth(month);
    }

    @Override
    public List<MovieDTO> comingSoon(Integer month) {
//        List<Movie> movies = findAllByReleaseDate();
//        if(month != -1)
//            movies=movies
//                    .stream()
//                    .filter(x->x.getReleaseDate().getMonth().getValue() == month)
//                    .collect(Collectors.toList());

        List<Movie> movies;
        if(month==-1) movies=findAllByReleaseDate();
        else{
            String monthString="";
            if(month<10) monthString+="0";
            monthString+=month;
            movies=findAllByReleaseMonth(monthString);
        }

        List<MovieDTO> movieDTOS=new ArrayList<>();

        movies.forEach(m->{
            List<Showtime> movieShowtimes=showtimeService.findAllByMovieId(m.getID());
            if(movieShowtimes.size() != 0) { // the movie has showtimes
                movieShowtimes
                        .stream()
                        .filter(x->x.getDate().isBefore(today.plusDays(7)))
                        .sorted(Comparator.comparing(Showtime::getDate))
                        .collect(Collectors.toList());
                LocalDate firstDate = movieShowtimes.get(0).getDate();
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m,firstDate));
            }
            else{
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m,null));
            }
        });

        return movieDTOS
                .stream()
                .sorted(Comparator.comparing(MovieDTO::getMovieReleaseDate))
                .collect(Collectors.toList());
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
    public  List<Movie> findAllByEndDate(){
        return movieRepository.findAllByEndDate();
    }

    @Override
    public List<MovieDTO> findAllAvailable() {
        List<Movie> movies=findAllByEndDate();
        List<MovieDTO> movieDTOS=new ArrayList<>();

        movies.forEach(m->{
            List<Showtime> movieShowtimes=showtimeService.findAllByMovieId(m.getID());
            if(movieShowtimes.size()!=0) {
                movieShowtimes
                        .stream()
                        .filter(x -> (x.getDate().isAfter(today) || x.getDate().isEqual(today)) && x.getDate().isBefore(today.plusDays(7)))
                        .sorted(Comparator.comparing(Showtime::getDate))
                        .collect(Collectors.toList());
                LocalDate firstDate = movieShowtimes.get(0).getDate();
                if (firstDate.isEqual(today)
                        && showtimeService.findAllTodayByMovieIdAndCurrentTime(m.getID()).size() == 0) {
                    int i = 0;
                    while (movieShowtimes.get(i).getDate().isEqual(today)) i++;
                    firstDate = movieShowtimes.get(i).getDate();
                }
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m, firstDate));
            }
            else{
                movieDTOS.add(converters.convertFromMovieToMovieDTO(m, null));
            }
        });

        return movieDTOS
                .stream()
                .sorted(Comparator.comparing(MovieDTO::getMovieTitle))
                .collect(Collectors.toList());

    }

    @Override
    public void delete() {
        movieRepository.deleteById(22);
    }
}
