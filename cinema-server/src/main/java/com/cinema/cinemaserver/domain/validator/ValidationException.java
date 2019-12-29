package com.cinema.cinemaserver.domain.validator;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID=134245222254729812L;

    public ValidationException(String message) {
        super(message);
    }
}
