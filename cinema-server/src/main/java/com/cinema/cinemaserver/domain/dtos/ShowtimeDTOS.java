package com.cinema.cinemaserver.domain.dtos;

import com.cinema.cinemaserver.domain.Movie;

import java.util.List;

public class ShowtimeDTOS {
    private List<ShowtimeDTO> showtimeDTOList;
    private Movie movie;

    public ShowtimeDTOS() { }

    public ShowtimeDTOS(List<ShowtimeDTO> showtimeDTOList, Movie movie) {
        this.showtimeDTOList = showtimeDTOList;
        this.movie = movie;
    }

    public List<ShowtimeDTO> getShowtimeDTOList() {
        return showtimeDTOList;
    }

    public void setShowtimeDTOList(List<ShowtimeDTO> showtimeDTOList) {
        this.showtimeDTOList = showtimeDTOList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
