package fr.insarouen.iti.compilation.sgbd;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import fr.insarouen.iti.compilation.sgbd.analyseurs.AnalyseurSyntaxique;
import fr.insarouen.iti.compilation.sgbd.analyseurs.ParseException;
import fr.insarouen.iti.compilation.sgbd.exceptions.PIAventureException;
import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.persistance.GestionnairePersistance;

public class Main {
    
    public static void main(String[] args) throws IOException {
        GestionnairePersistance gestionnaire = new GestionnairePersistance();
        // BaseDeDonnees bdd = gestionnaire.charger(args[0]);
        BaseDeDonnees bdd = new BaseDeDonnees("base");
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Base de données : %s\n", bdd.getIdentifiant());

        while (bdd.getEstOuverte()) {
            String commande = scanner.nextLine();
            while (!commande.contains(";")) {
                commande = commande + "\n" + scanner.nextLine();
            }

            if (!commande.isBlank()) {
                Reader reader = new StringReader(commande);

                AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(reader);
                analyseur.setBdd(bdd);

                try {
                    analyseur.commande();
                } catch (ParseException e) {
                    System.err.printf("Erreur : %s\n", e.getMessage());
                } catch (PIAventureException e) {
                    System.err.printf("Erreur : %s\n", e.getMessage());
                }
            }
        }
        // gestionnaire.sauvegarder(bdd);
        scanner.close();
    }
}