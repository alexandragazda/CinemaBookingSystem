package com.cinema.cinemaserver.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Screen implements HasID<Integer>{

    private static final long serialVersionUID = 3451271841240616581L;

    @Id
    private Integer ID;

    private Integer nrSeats;
    private Integer nrRows;
    private Integer nrCols;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<Showtime> showtimes = new HashSet<>();

    public Screen() {
    }

    public Screen(Integer ID, Integer nrSeats, Integer nrRows, Integer nrCols) {
        this.ID = ID;
        this.nrSeats = nrSeats;
        this.nrRows = nrRows;
        this.nrCols = nrCols;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getNrSeats() {
        return nrSeats;
    }

    public void setNrSeats(Integer nrSeats) {
        this.nrSeats = nrSeats;
    }

    public Integer getNrRows() {
        return nrRows;
    }

    public void setNrRows(Integer nrRows) {
        this.nrRows = nrRows;
    }

    public Integer getNrCols() {
        return nrCols;
    }

    public void setNrCols(Integer nrCols) {
        this.nrCols = nrCols;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    @Override
    public String toString() {
        return "Screen: " +
                "ID=" + ID + " | " +
                "nrSeats=" + nrSeats + " | " +
                "nrRows=" + nrRows + " | " +
                "nrCols=" + nrCols;
    }
}
