package com.iim.iimcoursspring.repository;

import com.iim.iimcoursspring.combattant.Archer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArcherRepository extends JpaRepository<Archer, Integer> {
}
