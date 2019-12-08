package com.cinema.cinemaserver.domain;

import java.io.Serializable;

public interface HasID<ID> extends Serializable {

    ID getID();

    void setID(ID id);
}
