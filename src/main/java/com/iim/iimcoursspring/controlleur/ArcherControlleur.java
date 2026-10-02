package com.iim.iimcoursspring.controlleur;

import com.iim.iimcoursspring.combattant.Archer;
import com.iim.iimcoursspring.service.ArcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Archer")
public class ArcherControlleur {

    private final ArcherService archerService;

    @Autowired
    public ArcherControlleur(ArcherService archerService){
        this.archerService = archerService;
    }

    @GetMapping
    public List<Archer> getAll(){
        return archerService.getAll();
    }

    @GetMapping("/{id}")
    public Archer getById(@PathVariable int id){
        return archerService.getById(id);
    }

    @PostMapping
    public Archer create(@RequestParam String name,
                         @RequestParam boolean hasBow,
                         @RequestParam int numberArrow,
                         @RequestParam(defaultValue = "70") int porter,
                         @RequestParam int xArcher){
        return archerService.create(name, hasBow, numberArrow, porter, xArcher);
    }
}
