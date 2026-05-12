package fr.insarouen.iti.compilation.sgbd;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import fr.insarouen.iti.compilation.sgbd.exceptions.PIAventureException;
import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.persistance.GestionnairePersistance;

public class Main {
    
    public static void main(String[] args) {
        GestionnairePersistance gestionnaire = new GestionnairePersistance();
        BaseDeDonnees baseDeDonnees = gestionnaire.charger(args[0]);
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Base de donnée: %s\n", args[0]);

        while (baseDeDonnees.estConnectee) {
            System.out.print("> ");
            String commande = scanner.nextLine();
            Reader reader = new StringReader(commande);
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(reader);

            try {
                analyseur.commande(baseDeDonnees);
            } catch (PIAventureException e) {
                System.err.println(e.getMessage());
            }
        }
        gestionnaire.sauvegarder(baseDeDonnees);
    }
}