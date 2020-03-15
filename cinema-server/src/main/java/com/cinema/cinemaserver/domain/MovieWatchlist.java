package com.cinema.cinemaserver.domain;

import javax.persistence.*;

@Entity
public class MovieWatchlist implements HasID<Integer> {

    private static final long serialVersionUID = 3471272844747956581L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    public MovieWatchlist() { }

    public MovieWatchlist(Movie movie, Watchlist watchlist) {
        this.movie = movie;
        this.watchlist = watchlist;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    @Override
    public String toString() {
        return "MovieWatchlist: " +
                "ID=" + ID + " | " +
                "movie=" + movie.getID() + " | " +
                "watchlist=" + watchlist.getID();
    }
}
