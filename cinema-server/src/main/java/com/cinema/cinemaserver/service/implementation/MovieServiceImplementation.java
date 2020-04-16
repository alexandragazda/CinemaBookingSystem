package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Movie;
import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.dtos.MovieDTO;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.MovieRepository;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ShowtimeService;
import com.cinema.cinemaserver.service.TicketService;
import com.cinema.cinemaserver.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private TicketService ticketService;

    //    @Autowired
//    private Validator<Movie> validator;

    @Autowired
    private Converters converters;

    private static final LocalDate today=LocalDate.now(); // !!!!!!!!!! today

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
    public List<Movie> findAll() {
        return movieRepository.findAll();
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

        for (Movie m: movies) {
            List<Showtime> movieShowtimes=showtimeService.findAllByMovieId(m.getID());
            if(movieShowtimes.size()!=0) {
                movieShowtimes = movieShowtimes
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
        return movieRepository.findAllUpcomingByReleaseMonth(month);
    }

    @Override
    public List<MovieDTO> comingSoon(Integer month) {
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
                movieShowtimes = movieShowtimes
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
    public  List<Movie> findAllByEndDate(){
        return movieRepository.findAllByEndDate();
    }

    @Override
    public List<MovieDTO> findAllAvailable() {
        List<Movie> movies=findAllByEndDate();

        return getMovieDTOList(movies)
                .stream()
                .sorted(Comparator.comparing(MovieDTO::getMovieTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findTop() {
        List<Movie> movies=findAllByEndDate();

        movies = movies.stream().filter(x->showtimeService.findAllByMovieId(x.getID()).size() > 0).collect(Collectors.toList());

        Map<Movie,Integer> movieMap=new LinkedHashMap<>();
        for (Movie m: movies) {
            movieMap.put(m,ticketService.findAllByMovieID(m.getID()).size());
        }

        //sort the map in reverse order
        movieMap = movieMap.entrySet()
                .stream()
                .sorted((Map.Entry.<Movie, Integer>comparingByValue().reversed()))
                .limit(5) // get top 5
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return getMovieDTOList(new ArrayList<>(movieMap.keySet()));
    }

    @Override
    public List<MovieDTO> getMovieDTOList(List<Movie> movies) {
        List<MovieDTO> movieDTOS=new ArrayList<>();

        movies.forEach(m->{
            List<Showtime> movieShowtimes=showtimeService.findAllByMovieId(m.getID());
            if(movieShowtimes.size()!=0) {
                movieShowtimes = movieShowtimes
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

        return movieDTOS;
    }
}
