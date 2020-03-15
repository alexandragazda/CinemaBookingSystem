package com.cinema.cinemaserver.domain;

import com.cinema.cinemaserver.domain.enums.AgeRating;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie implements HasID<Integer>{

    private static final long serialVersionUID= 4571271118840909501L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;

    private String title;
    private String genre;
    @Column(length = 3000)
    private String cast;
    private String director;
    @Column(length = 5000)
    private String description;
    private Integer duration; //minutes
    private AgeRating ageRating;
    private LocalDate releaseDate;
    private LocalDate endDate; //the date until it will appear in cinema
    private String availableTechnology;
    private String trailer; //part of the URL (after v= and before the first &)
    @Lob
    @Column(name = "poster", columnDefinition = "LONGBLOB")
    private byte[] poster;
    private String linkIMDb;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<Showtime> showtimes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieWatchlist> movieWatchlistSet = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, String genre, String cast, String director, Integer duration, AgeRating ageRating, LocalDate releaseDate, LocalDate endDate, String availableTechnology, String linkIMDb, String trailer, byte[] poster, String description) {
        this.title = title;
        this.genre = genre;
        this.cast = cast;
        this.director = director;
        this.duration = duration;
        this.ageRating = ageRating;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.availableTechnology = availableTechnology;
        this.linkIMDb = linkIMDb;
        this.trailer = trailer;
        this.poster = poster;
        this.description = description;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAvailableTechnology() {
        return availableTechnology;
    }

    public void setAvailableTechnology(String availableTechnology) {
        this.availableTechnology = availableTechnology;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public String getLinkIMDb() {
        return linkIMDb;
    }

    public void setLinkIMDb(String linkIMDb) {
        this.linkIMDb = linkIMDb;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public Set<MovieWatchlist> getMovieWatchlistSet() {
        return movieWatchlistSet;
    }

    public void addMovieWatchlist(MovieWatchlist movieWatchlist) {
        movieWatchlistSet.add(movieWatchlist);
        movieWatchlist.setMovie(this);
    }

    public void removeMovieWatchlist(MovieWatchlist movieWatchlist) {
        movieWatchlistSet.remove(movieWatchlist);
        movieWatchlist.setMovie(null);
    }

    @Override
    public String toString() {
        return "Movie: " +
                "ID=" + ID + " | " +
                "title=" + title + " | " +
                "genre=" + genre + " | " +
                "cast=" + cast + " | " +
                "director=" + director + " | " +
                "description=" + description + " | " +
                "duration=" + duration + "min" + " | " +
                "rating=" + ageRating + " | " +
                "releaseDate=" + releaseDate + " | " +
                "endDate=" + endDate + " | " +
                "availableTechnology=" + availableTechnology + " | " +
                "trailer=" + trailer + " | " +
                "poster=" + Arrays.toString(poster) + " | " +
                "linkIMDb=" + linkIMDb;
    }
}
