package com.cinema.cinemaserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ConcessionType implements HasID<String> {

    private static final long serialVersionUID = 8923781246740911581L;

    @Id
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "concessionType", cascade = CascadeType.ALL) //by default, the fetch type is lazy
    private Set<Concession> concessions = new HashSet<>();

    public ConcessionType() { }

    public ConcessionType(String type) {
        this.type = type;
    }

    public String getID() {
        return type;
    }

    public void setID(String type) {
        this.type = type;
    }

    public Set<Concession> getConcessions() {
        return concessions;
    }

    public void addConcession(Concession concession) {
        concessions.add(concession);
        concession.setConcessionType(this);
    }

    public void removeConcession(Concession concession) {
        concessions.remove(concession);
        concession.setConcessionType(null);
    }

    @Override
    public String toString() {
        return "ConcessionType: " +
                "type=" + type;
    }
}
