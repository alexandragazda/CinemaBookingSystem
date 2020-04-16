package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Showtime;
import com.cinema.cinemaserver.domain.dtos.ShowtimeDTO;
import com.cinema.cinemaserver.domain.dtos.ShowtimeDTOS;
import com.cinema.cinemaserver.repository.ShowtimeRepository;
import com.cinema.cinemaserver.service.MovieService;
import com.cinema.cinemaserver.service.ShowtimeService;
import com.cinema.cinemaserver.utils.BookingUtils;
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
public class ShowtimeServiceImplementation implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private MovieService movieService;

    @Autowired
    private BookingUtils bookingUtils;

    @Autowired
    private Converters converters;

    private static final LocalDate today=LocalDate.now();

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
        return showtimeRepository.findAllByMovieID(movieId);
    }

    @Override
    public List<Showtime> findAllByMovieIdAndDate(Integer movieId, LocalDate date) {
        if(date.isEqual(today)) {
            //return the showtimes sorted by time
            return findAllTodayByMovieIdAndCurrentTime(movieId)
                    .stream()
                    .sorted(Comparator.comparing(Showtime::getTime))
                    .collect(Collectors.toList());
        }
        //return the showtimes sorted by time
        return showtimeRepository.findAllByMovieIDAndDate(movieId, date)
                .stream()
                .sorted(Comparator.comparing(Showtime::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> findAllTodayByMovieIdAndCurrentTime(Integer movieId){
        return showtimeRepository.findAllTodayByMovieIDAndCurrentTime(movieId, LocalTime.now().plusMinutes(20));
    }

    @Override
    public List<Showtime> findAllByMovieIdAndDateAvailable(Integer movieId, LocalDate date) {
        List<Showtime> showtimes=findAllByMovieIdAndDate(movieId,date);
        List<Showtime> returnedShowtimes=new ArrayList<>();
        showtimes.forEach(x->{
            List<List<Integer>> stateOfSeats= bookingUtils.stateOfSeats(x.getID());
            boolean free=false;
            for(int i=0;i<stateOfSeats.size();i++){
                for(int j=0;j<stateOfSeats.get(0).size();j++){
                    if(stateOfSeats.get(i).get(j)==0){
                        free=true;
                        break;
                    }
                }
            }
            if(free==true) returnedShowtimes.add(x);
        });

        return returnedShowtimes;
    }

    @Override
    public ShowtimeDTOS findShowtimeDTOSByMovieIdAndDate(Integer movieId, LocalDate date) {
        List<Showtime> showtimes = findAllByMovieIdAndDate(movieId,date);

        ShowtimeDTOS showtimeDTOS=new ShowtimeDTOS();
        List<ShowtimeDTO> showtimeDTOList=new ArrayList<>();
        showtimes.forEach(x->showtimeDTOList.add(converters.convertFromShowtimeToShowtimeDTO(x)));
        showtimeDTOS.setShowtimeDTOList(showtimeDTOList);
        showtimeDTOS.setMovie(movieService.findById(movieId));

        return showtimeDTOS;
    }
}
