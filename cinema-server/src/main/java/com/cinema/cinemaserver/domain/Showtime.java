package com.cinema.cinemaserver.domain;

import com.cinema.cinemaserver.domain.enums.Technology;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Showtime implements HasID<Integer>{

    private static final long serialVersionUID= 4341271348847809505L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private LocalDate date;
    private LocalTime time;
    private Technology technology;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY) //by default, the fetch type is eager
    @JoinColumn
   // @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@ID")
    private Screen screen;

    public Showtime() {
    }

    public Showtime(LocalDate date, LocalTime time, Technology technology, Movie movie, Screen screen) {
        this.date = date;
        this.time = time;
        this.technology = technology;
        this.movie = movie;
        this.screen = screen;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Showtime: " +
                "ID" + ID + " | " +
                "date=" + date + " | "+
                "time=" + time + " | " +
                "technology=" + technology + " | "+
                "movie=" + movie.getID() + " | " +
                "screen=" + screen.getID();
    }
}
