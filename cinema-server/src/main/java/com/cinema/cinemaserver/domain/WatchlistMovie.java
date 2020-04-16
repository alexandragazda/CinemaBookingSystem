package com.cinema.cinemaserver.domain;

import javax.persistence.*;

@Entity
public class WatchlistMovie implements HasID<Integer> {

    private static final long serialVersionUID = 3471272844747956581L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Movie movie;

    public WatchlistMovie() { }

    public WatchlistMovie(Watchlist watchlist, Movie movie) {
        this.watchlist = watchlist;
        this.movie = movie;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "WatchlistMovie: " +
                "ID=" + ID + " | " +
                "watchlist=" + watchlist.getID() + " | " +
                "movie=" + movie.getID();
    }
}
