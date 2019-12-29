package com.cinema.cinemaserver.domain.validator;

public interface Validator<E> {
    void validate(E entity);
}
