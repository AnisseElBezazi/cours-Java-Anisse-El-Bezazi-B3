package com.iim.iimcoursspring.service;

import com.iim.iimcoursspring.combattant.Archer;
import com.iim.iimcoursspring.repository.ArcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ArcherService {

    private final ArcherRepository archerRepository;

    @Autowired
    public ArcherService(ArcherRepository archerRepository) {
        this.archerRepository = archerRepository;
    }

    public Archer create(String name, boolean hasBow, int numberArrow, int porter, int xArcher) {
        Archer archer = new Archer(name, hasBow, numberArrow, porter, xArcher);
        return archerRepository.save(archer);
    }

    public List<Archer> getAll() {
        return archerRepository.findAll();
    }

    public Archer getById(int id) {
        return archerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Archer introuvable"));
    }
}
