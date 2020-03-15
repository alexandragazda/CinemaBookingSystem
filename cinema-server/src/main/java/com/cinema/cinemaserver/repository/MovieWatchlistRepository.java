package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.MovieWatchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieWatchlistRepository extends JpaRepository<MovieWatchlist, Integer> {
    //returns all the moviewatchlist entities with the given watchlist ID
    @Query("select mw from MovieWatchlist mw inner join Watchlist w on w.ID = mw.watchlist.ID where w.ID = ?1")
    List<MovieWatchlist> findAllByWatchlistID(String watchlistID);

    //return all the moviewatchlist entities with the given watchlist ID and movie ID
    @Query("select mw from MovieWatchlist mw " +
            "inner join Watchlist w on w.ID = mw.watchlist.ID " +
            "inner join Movie m on m.ID = mw.movie.ID " +
            "where w.ID = ?1 and m.ID=?2")
    MovieWatchlist findAllByWatchlistIDAndMovieID(String watchlistID, Integer movieID);
}
