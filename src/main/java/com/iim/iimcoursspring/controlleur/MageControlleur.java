package com.iim.iimcoursspring.controlleur;

import com.iim.iimcoursspring.combattant.Mage;
import com.iim.iimcoursspring.service.MageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Mage")
public class MageControlleur {

    private final MageService mageService;

    @Autowired
    public MageControlleur(MageService mageService){
        this.mageService = mageService;
    }

    @GetMapping
    public List<Mage> getAll(){
        return mageService.getAll();
    }

    @GetMapping("/{id}")
    public Mage getById(@PathVariable int id){
        return mageService.getById(id);
    }

    @PostMapping
    public Mage create(@RequestParam String name,
                       @RequestParam int hasMana,
                       @RequestParam(defaultValue = "70") int porter,
                       @RequestParam int xMage,
                       @RequestParam float spellPuissanceMultiplier){
        return mageService.create(name, hasMana, porter, xMage, spellPuissanceMultiplier);
    }

}
