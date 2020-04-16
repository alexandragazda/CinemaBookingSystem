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

    //returns the movie with a specified title
    Movie findByTitle(String title);

    //returns all the distinct movies witch have showtimes on a specific date
    @Query("select distinct m from Movie m inner join Showtime s on m.ID = s.movie.ID where s.date=?1")
    List<Movie> findAllByDate(LocalDate date);

    //returns all the distinct movies witch have showtimes today after current time
    @Query("select distinct m from Movie m inner join Showtime s on m.ID = s.movie.ID where s.date=current_date and s.time > ?1 ")
    List<Movie> findAllTodayByCurrentTime(LocalTime time);

    //returns all the movies witch appear in a given watchlist
    @Query("select m from Movie m inner join WatchlistMovie wm on m.ID = wm.movie.ID where wm.watchlist.ID=?1")
    List<Movie> findAllByWatchlistID(String watchlistID);

    //returns all the movies witch have the release date after today
    @Query("select m from Movie m where m.releaseDate>current_date ")
    List<Movie> findAllByReleaseDate();

    //returns all the upcoming movies witch have the release month equal to the given month
    @Query("select m from Movie m where m.releaseDate>current_date and substring(m.releaseDate,6,2) =?1")
    List<Movie> findAllUpcomingByReleaseMonth(String month);

    //returns all the movies witch have the end date after today
    @Query("select m from Movie m where m.endDate>current_date")
    List<Movie> findAllByEndDate();
}
