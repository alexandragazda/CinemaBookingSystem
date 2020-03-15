package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.dtos.MovieWatchlistDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieWatchlistDTOValidator implements Validator<MovieWatchlistDTO> {
    @Override
    public void validate(MovieWatchlistDTO entity) {
        String msg="";

        if(entity.getMovieID() == null) msg+="MovieID cannot be empty!";
        if(entity.getWatchlistID() == null) msg+="WatchlistID cannot be empty!";

        if (msg != "") {
            throw new ValidationException(msg);
        }
    }
}
