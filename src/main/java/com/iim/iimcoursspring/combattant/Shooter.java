package com.iim.iimcoursspring.combattant;

import com.iim.iimcoursspring.entity.Cible;

public interface Shooter {
    boolean canShoot();
    void shoot(Cible cible);
    int getPosition();
    int getPorter();
    void seDeplacer(int deplacement);
    void recuperer();
}
