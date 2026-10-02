package com.iim.iimcoursspring.service;

import com.iim.iimcoursspring.combattant.Mage;
import com.iim.iimcoursspring.repository.MageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MageService {

    private final MageRepository mageRepository;

    @Autowired
    public MageService(MageRepository mageRepository) {
        this.mageRepository = mageRepository;
    }

    public Mage create(String name, int hasMana, int porter, int xMage, float spellPuissanceMultiplier) {
        Mage mage = new Mage(name, hasMana, porter, xMage, spellPuissanceMultiplier);
        return mageRepository.save(mage);
    }

    public List<Mage> getAll() {
        return mageRepository.findAll();
    }

    public Mage getById(int id) {
        return mageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mage introuvable"));
    }
}
