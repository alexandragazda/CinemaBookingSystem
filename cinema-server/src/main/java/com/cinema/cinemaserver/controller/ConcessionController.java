package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Concession;
import com.cinema.cinemaserver.service.ConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ConcessionController {
    @Autowired
    private ConcessionService concessionService;

    @GetMapping("/concessions")
    public ResponseEntity<List<Concession>> findAll(){
        List<Concession> concessions = concessionService.findAll();
        return ResponseEntity.ok().body(concessions);
    }
}
