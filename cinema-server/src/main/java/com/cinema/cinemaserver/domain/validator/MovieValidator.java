package com.cinema.cinemaserver.domain.validator;

import com.cinema.cinemaserver.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie entity) {
        String msg="";

        if(entity.getTitle().equals("")) msg+="Title cannot be empty!";
        if(entity.getGenre().equals("")) msg+="Genre cannot be empty!";
        if(entity.getCast().equals("")) msg+="Cast cannot be empty!";
        if(entity.getCast().length()>3000) msg+="Cast is too long!";
        if(entity.getDirector().equals("")) msg+="Director cannot be empty!";
        if(entity.getAgeRating()==null) msg+="You have to specify the age rating!";
        if(entity.getDescription().equals("")) msg+="Description cannot be empty";
        if(entity.getDescription().length()>5000) msg+="Description is too long!";
        if(entity.getReleaseDate()==null) msg+="You have to specify the release date!";
        if(entity.getEndDate()==null) msg+="You have to specify the end date!";
        if(entity.getReleaseDate().isAfter(entity.getEndDate()) || entity.getReleaseDate().isEqual(entity.getEndDate())) msg+="Invalid dates!";
        if(entity.getDuration()<=0) msg+="Duration is invalid!";
        if(entity.getTrailer().equals("")) msg+="Trailer cannot be empty!";
        if(entity.getPoster().length<=0) msg+="You have to specify the poster path!";
        if(entity.getLinkIMDb().equals("")) msg+="You have to specify the IMDB link!";

        if (msg != "") {
            throw new ValidationException(msg);
        }
    }
}
