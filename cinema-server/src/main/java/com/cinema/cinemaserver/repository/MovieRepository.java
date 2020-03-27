package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByTitle(String title);

    //returns all the distinct movies witch have showtimes on a specific date
    @Query("select distinct m from Movie m inner join Showtime s on m.ID = s.movie.ID where s.date=?1")
    List<Movie> findAllByDate(LocalDate date);

    //returns all the distinct movies witch have showtimes today after current time
    @Query("select distinct m from Movie m inner join Showtime s on m.ID = s.movie.ID where s.date='2020-03-19' and s.time > ?1 ") //!!!!!! today
    List<Movie> findAllTodayByCurrentTime(LocalTime time);

    //returns all the movies witch appear in a given watchlist
    @Query("select m from Movie m inner join WatchlistMovie wm on m.ID = wm.movie.ID where wm.watchlist.ID=?1")
    List<Movie> findAllByWatchlistID(String watchlistID);
}
