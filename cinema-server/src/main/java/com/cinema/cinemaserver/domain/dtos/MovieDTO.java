package com.cinema.cinemaserver.domain.dtos;

import java.time.LocalDate;
import java.util.Arrays;

public class MovieDTO {
    private Integer movieID;
    private byte[] moviePoster;
    private String movieTitle;
    private String movieLinkIMDb;
    private LocalDate movieReleaseDate;

    private LocalDate firstDate; //the movie's first date of showtime

    public MovieDTO() { }

    public MovieDTO(Integer movieID, byte[] moviePoster, String movieTitle, String movieLinkIMDb, LocalDate movieReleaseDate, LocalDate firstDate) {
        this.movieID = movieID;
        this.moviePoster = moviePoster;
        this.movieTitle = movieTitle;
        this.movieLinkIMDb = movieLinkIMDb;
        this.movieReleaseDate = movieReleaseDate;
        this.firstDate = firstDate;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public byte[] getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(byte[] moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieLinkIMDb() {
        return movieLinkIMDb;
    }

    public void setMovieLinkIMDb(String movieLinkIMDb) {
        this.movieLinkIMDb = movieLinkIMDb;
    }

    public LocalDate getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    @Override
    public String toString() {
        return "MovieDTO: " +
                "firstDate=" + firstDate + " |  "+
                "movieID=" + movieID + " | " +
                "movieTitle=" + movieTitle + " | " +
                "linkIMDb=" + movieLinkIMDb + " | " +
                "movieReleaseDate=" + movieReleaseDate + " | " +
                "moviePoster=" + Arrays.toString(moviePoster);
    }
}
