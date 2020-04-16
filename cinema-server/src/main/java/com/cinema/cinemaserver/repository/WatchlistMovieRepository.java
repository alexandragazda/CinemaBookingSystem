package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.WatchlistMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistMovieRepository extends JpaRepository<WatchlistMovie, Integer> {

    //returns all the watchlistmovie entities from a given watchlist
    @Query("select mw from WatchlistMovie mw inner join Watchlist w on w.ID = mw.watchlist.ID where w.ID = ?1")
    List<WatchlistMovie> findAllByWatchlistID(String watchlistID);

    //returns all the watchlistmovie entities with the given watchlist ID and movie ID
    @Query("select mw from WatchlistMovie mw " +
            "inner join Watchlist w on w.ID = mw.watchlist.ID " +
            "inner join Movie m on m.ID = mw.movie.ID " +
            "where w.ID = ?1 and m.ID=?2")
    WatchlistMovie findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID);
}
