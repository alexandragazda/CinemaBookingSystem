package com.cinema.cinemaserver.domain.dtos;

public class MovieWatchlistDTO {
    private Integer movieID;
    private String watchlistID;

    public MovieWatchlistDTO() { }

    public MovieWatchlistDTO(Integer movieID, String watchlistID) {
        this.movieID = movieID;
        this.watchlistID = watchlistID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getWatchlistID() {
        return watchlistID;
    }

    public void setWatchlistID(String watchlistID) {
        this.watchlistID = watchlistID;
    }

    @Override
    public String toString() {
        return "MovieWatchlistDTO: " +
                "movieID=" + movieID + " | " +
                "watchlistID=" + watchlistID;
    }
}
