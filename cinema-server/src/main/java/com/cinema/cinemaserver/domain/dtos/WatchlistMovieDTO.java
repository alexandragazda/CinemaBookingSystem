package com.cinema.cinemaserver.domain.dtos;

public class WatchlistMovieDTO {
    private String watchlistID;
    private Integer movieID;

    public WatchlistMovieDTO() { }

    public WatchlistMovieDTO(String watchlistID, Integer movieID) {
        this.watchlistID = watchlistID;
        this.movieID = movieID;
    }

    public String getWatchlistID() {
        return watchlistID;
    }

    public void setWatchlistID(String watchlistID) {
        this.watchlistID = watchlistID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "MovieWatchlistDTO: " +
                "watchlistID=" + watchlistID + " | " +
                "movieID=" + movieID;
    }
}
