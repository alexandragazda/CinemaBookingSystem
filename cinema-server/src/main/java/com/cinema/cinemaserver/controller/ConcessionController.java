package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.Concession;
import com.cinema.cinemaserver.domain.ConcessionType;
import com.cinema.cinemaserver.service.ConcessionService;
import com.cinema.cinemaserver.service.ConcessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ConcessionController {
    @Autowired
    private ConcessionService concessionService;

    @Autowired
    private ConcessionTypeService concessionTypeService;

//    @GetMapping
//    public String welcome(){
//        concessionTypeService.save(new ConcessionType("Food"));
//        concessionTypeService.save(new ConcessionType("Drink"));
//        concessionTypeService.save(new ConcessionType("Menu"));
//
//        concessionService.save(new Concession("Popcorn Small", "65g",8.0,concessionTypeService.findByID("Food")));
//        concessionService.save(new Concession("Popcorn Medium", "120g",10.0,concessionTypeService.findByID("Food")));
//        concessionService.save(new Concession("Coca-Cola Medium", "0.75l", 7.5,concessionTypeService.findByID("Drink")));
//        concessionService.save(new Concession("Large Popcorn Menu", "Large Popcorn 220g & Large Drink 1l", 21.0, concessionTypeService.findByID("Menu")));
//
//        return "welcome";
//    }

    @GetMapping("/concessiontypes")
    public List<ConcessionType> concessionTypes() {
        return concessionTypeService.findAll();
    }

//    @GetMapping("/concessions")
//    public ResponseEntity<List<Concession>> findAllByConcessionType(@RequestParam("concessionType") String concessionType){
//        System.out.println(concessionType);
//        List<Concession> concessions = concessionService.findAllByConcessionType(concessionType);
//        return ResponseEntity.ok().body(concessions);
//    }

    @GetMapping("/concessions")
    public ResponseEntity<List<Concession>> findAll(){
        List<Concession> concessions = concessionService.findAll();
        return ResponseEntity.ok().body(concessions);
    }
}
