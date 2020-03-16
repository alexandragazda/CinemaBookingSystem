package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Watchlist implements HasID<String>{

    private static final long serialVersionUID = 5571271745540956581L;

    @Id
    private String ID;

    @OneToOne
    @MapsId
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL)
    private Set<WatchlistMovie> watchlistMovieSet = new HashSet<>();

    public Watchlist() { }

    public Watchlist(User user) {
        this.user = user;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<WatchlistMovie> getWatchlistMovieSet() {
        return watchlistMovieSet;
    }

    public void addWatchlistMovie(WatchlistMovie watchlistMovie) {
        watchlistMovieSet.add(watchlistMovie);
        watchlistMovie.setWatchlist(this);
    }

    public void removeWatchlistMovie(WatchlistMovie watchlistMovie) {
        watchlistMovieSet.remove(watchlistMovie);
        watchlistMovie.setWatchlist(null);
    }

    @Override
    public String toString() {
        return "Watchlist: " +
                "ID=" + ID + " | " +
                "user=" + user.getID();
    }
}
