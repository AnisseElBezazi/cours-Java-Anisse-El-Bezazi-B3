package com.iim.iimcoursspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestClient;
import com.iim.iimcoursspring.combattant.Archer;
import com.iim.iimcoursspring.combattant.Mage;
import com.iim.iimcoursspring.combattant.Shooter;
import java.util.*;
import com.iim.iimcoursspring.entity.Cible;

@SpringBootApplication
public class IiMcoursSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IiMcoursSpringApplication.class, args);

        String port = context.getEnvironment().getProperty("local.server.port", "8080");
        RestClient api = RestClient.create("http://localhost:" + port);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Creation de l'archer");

        System.out.println("Nom de l'archer :");
        String nameArcher = scanner.next();

        String repArc = "";
        while (!repArc.equals("y") && !repArc.equals("n")) {
            System.out.println("L'archer a un arc ? (y/n)");
            repArc = scanner.next();
        }
        boolean hasBow = repArc.equals("y");

        int numberArrow = -1;
        while (numberArrow < 0 || numberArrow > 10) {
            System.out.println("Nombre de fleches (0 a 10) :");
            if (scanner.hasNextInt()) numberArrow = scanner.nextInt();
            else scanner.next();
        }

        int xArcher = 5000;
        while (xArcher < 0 || xArcher > 50) {
            System.out.println("Position de l'archer (0 a 50) :");
            if (scanner.hasNextInt()) xArcher = scanner.nextInt();
            else scanner.next();
        }

        Archer archer = api.post()
                .uri("/Archer?name={name}&hasBow={hasBow}&numberArrow={numberArrow}&xArcher={xArcher}",
                        nameArcher, hasBow, numberArrow, xArcher)
                .retrieve()
                .body(Archer.class);

        System.out.println("\nCreation du mage");

        System.out.println("Nom du mage :");
        String nameMage = scanner.next();

        int hasMana = -1;
        while (hasMana < 0 || hasMana > 100) {
            System.out.println("Mana du mage (0 a 100) :");
            if (scanner.hasNextInt()) hasMana = scanner.nextInt();
            else scanner.next();
        }

        int xMage = 5000;
        while (xMage < 0 || xMage > 50) {
            System.out.println("Position du mage (0 a 50) :");
            if (scanner.hasNextInt()) xMage = scanner.nextInt();
            else scanner.next();
        }

        int pourcentage = 0;
        while (pourcentage < 1 || pourcentage > 100) {
            System.out.println("Puissance du sort en % (1 a 100) :");
            if (scanner.hasNextInt()) pourcentage = scanner.nextInt();
            else scanner.next();
        }
        float multiplier = pourcentage / 100f;

        Mage mage = api.post()
                .uri("/Mage?name={name}&hasMana={hasMana}&xMage={xMage}&spellPuissanceMultiplier={multiplier}",
                        nameMage, hasMana, xMage, multiplier)
                .retrieve()
                .body(Mage.class);

        int nbTours = 0;
        while (nbTours < 1 || nbTours > 100) {
            System.out.println("\nNombre de tours (1 a 100) :");
            if (scanner.hasNextInt()) nbTours = scanner.nextInt();
            else scanner.next();
        }

        if (nbTours % 2 != 0) {
            nbTours++;
            System.out.println("Nombre impair, on passe a " + nbTours);
        }

        Cible cible1 = new Cible(100, 10);

        for (int tour = 1; tour <= nbTours; tour++) {
            System.out.println("\nTour " + tour);

            cible1.positionCible();
            System.out.println("Position cible : " + cible1.PostionCibleTir);

            Shooter joueur = tour % 2 != 0 ? archer : mage;
            String nom = tour % 2 != 0 ? archer.name : mage.name;
            System.out.println("C'est a " + nom + " de jouer");

            int choix = 0;
            while (choix < 1 || choix > 3) {
                System.out.println("Action : 1 tirer, 2 deplacer, 3 recuperer");
                if (scanner.hasNextInt()) choix = scanner.nextInt();
                else scanner.next();
            }

            if (choix == 1) {
                joueur.shoot(cible1);
            } else if (choix == 2) {
                int deplacement = 100;
                while (deplacement < -20 || deplacement > 20) {
                    System.out.println("Deplacement (-20 a 20) :");
                    if (scanner.hasNextInt()) deplacement = scanner.nextInt();
                    else scanner.next();
                }
                joueur.seDeplacer(deplacement);
            } else {
                joueur.recuperer();
            }

            System.out.println("Position de " + nom + " : " + joueur.getPosition());
            if (tour % 2 != 0) System.out.println("Fleches restantes : " + archer.numberArrow);
            else System.out.println("Mana restant : " + mage.hasMana);
        }

        System.out.println("\nFin de la partie");
        System.out.println("Score de " + archer.name + " : " + archer.score);
        System.out.println("Score de " + mage.name + " : " + mage.score);

        if (archer.score > mage.score) {
            System.out.println(archer.name + " gagne !");
        } else if (mage.score > archer.score) {
            System.out.println(mage.name + " gagne !");
        } else {
            System.out.println("Egalite !");
        }

        scanner.close();
    }
}
