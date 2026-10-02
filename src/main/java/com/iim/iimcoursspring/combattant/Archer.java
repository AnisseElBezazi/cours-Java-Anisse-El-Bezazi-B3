package com.iim.iimcoursspring.combattant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iim.iimcoursspring.entity.Cible;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Archer implements Shooter {

    public static final int FLECHES_MAX = 10;
    private static int cpt = 0;

    @Id
    public int id;
    public String name;
    public boolean hasBow;
    public int numberArrow;
    public int porter;
    public boolean canShoot;
    public int Xarcher;
    public int score;

    @JsonCreator
    public Archer() {
    }

    public Archer(String nameParam, boolean hasBowParam, int numberArrowParam, int porterParam, int XarcherParam) {
        cpt++;
        this.id = cpt;
        this.name = nameParam;
        this.hasBow = hasBowParam;
        this.numberArrow = numberArrowParam;
        this.porter = porterParam;
        this.Xarcher = XarcherParam;
        canShoot();
    }

    @Override
    public boolean canShoot() {
        this.canShoot = (this.numberArrow >= 1 && this.hasBow);
        return this.canShoot;
    }

    @Override
    public void shoot(Cible cible) {
        if (canShoot()) {
            this.numberArrow--;
            if (Math.abs(this.Xarcher - cible.PostionCibleTir) <= this.porter) {
                System.out.println("L'archer a touche sa cible");
                int distance = Math.abs(this.Xarcher - cible.PostionCibleTir);
                int points = this.porter - distance;
                this.score += points;
                System.out.println("L'archer gagne " + points + " points");
            } else {
                System.out.println("L'archer a rate");
            }
        } else {
            if (!this.hasBow) {
                System.out.println("L'archer n'a pas d'arc");
            } else {
                System.out.println("L'archer n'a plus de fleches");
            }
        }
    }

    @Override
    public int getPorter() {
        return this.porter;
    }

    @Override
    public int getPosition() {
        return this.Xarcher;
    }

    @Override
    public void seDeplacer(int deplacement)  {
        this.Xarcher += deplacement;
        System.out.println("L'archer se deplace de " + deplacement);
    }

    @Override
    public void recuperer() {
        int anciennesFleches = this.numberArrow;
        this.numberArrow = Math.min(this.numberArrow + 2, FLECHES_MAX);
        System.out.println("L'archer recupere " + (this.numberArrow - anciennesFleches) + " fleches");
    }
}
