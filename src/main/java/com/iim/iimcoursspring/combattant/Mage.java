package com.iim.iimcoursspring.combattant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iim.iimcoursspring.entity.Cible;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mage implements Shooter  {

    public static final int COUT_MANA = 10;
    public static final int MANA_MAX = 100;
    private static int cpt = 0;

    @Id
    public int id;
    public String name;
    public int hasMana;
    public int porter;
    public boolean canShoot;
    public int XMage;
    public float SpellPuissanceMultiplier;
    public float Puissance;
    public int score;

    @JsonCreator
    public Mage() {
    }

    public Mage(String nameParam, int hasManaParam, int porterParam, int XMageParam, float SpellPuissanceMultiplierParam) {
        cpt++;
        this.id = cpt;
        this.name = nameParam;
        this.hasMana = hasManaParam;
        this.porter = porterParam;
        this.XMage = XMageParam;
        this.SpellPuissanceMultiplier = SpellPuissanceMultiplierParam;
        this.Puissance = this.SpellPuissanceMultiplier * 100;
        canShoot();
    }

    @Override
    public boolean canShoot() {
        this.canShoot = (this.hasMana >= COUT_MANA);
        return this.canShoot;
    }


    @Override
    public void shoot(Cible cible) {
        if (canShoot()) {
            this.hasMana -= COUT_MANA;

            if (Math.abs(this.XMage - cible.PostionCibleTir) <= this.porter) {
                System.out.println("Le mage a touche sa cible");
                int distance = Math.abs(this.XMage - cible.PostionCibleTir);
                int points = (int) ((this.porter - distance) * this.Puissance / 100);
                this.score += points;
                System.out.println("Le mage gagne " + points + " points");
            } else {
                System.out.println("Le mage a rate sa cible");
            }
        } else {
            System.out.println("Le mage n'a plus de mana");
        }
    }

    @Override
    public int getPorter() {
        return this.porter;
    }

    @Override
    public int getPosition() {
        return this.XMage;
    }

    @Override
    public void seDeplacer(int deplacement){
        this.XMage += deplacement;
        System.out.println("Le mage se deplace de " + deplacement);
    }

    @Override
    public void recuperer() {
        int ancienMana = this.hasMana;
        this.hasMana = Math.min(this.hasMana + 2 * COUT_MANA, MANA_MAX);
        System.out.println("Le mage recupere " + (this.hasMana - ancienMana) + " de mana");
    }
}
