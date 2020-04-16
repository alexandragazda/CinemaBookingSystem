package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.dtos.WatchlistMovieDTO;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMovieDTOValidator implements Validator<WatchlistMovieDTO> {
    @Override
    public void validate(WatchlistMovieDTO entity) {
        String msg="";

        if(entity.getMovieID() == null) msg+="MovieID cannot be empty!";
        if(entity.getWatchlistID() == null) msg+="WatchlistID cannot be empty!";

        if (!msg.equals("")) {
            throw new ValidationException(msg);
        }
    }
}
