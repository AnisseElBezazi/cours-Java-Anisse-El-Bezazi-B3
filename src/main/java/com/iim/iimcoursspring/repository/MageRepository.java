package com.iim.iimcoursspring.repository;

import com.iim.iimcoursspring.combattant.Mage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MageRepository extends JpaRepository<Mage, Integer> {
}
