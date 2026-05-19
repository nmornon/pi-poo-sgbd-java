package fr.insarouen.iti.compilation.sgbd;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import fr.insarouen.iti.compilation.sgbd.analyseurs.Interpreteur;
import fr.insarouen.iti.compilation.sgbd.analyseurs.TokenMgrError;
import fr.insarouen.iti.compilation.sgbd.analyseurs.ParseException;
import fr.insarouen.iti.compilation.sgbd.exceptions.SGBDException;
import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.persistance.GestionnairePersistance;

public class Main {
    
    public static void main(String[] args) throws IOException {
        GestionnairePersistance gestionnaire = new GestionnairePersistance();
        BaseDeDonnees bdd = gestionnaire.charger(args[0]);
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Base de données : %s\n", bdd.getIdentifiant());

        while (bdd.getEstOuverte()) {
            System.out.print("> ");
            String commande = scanner.nextLine();

            if (!commande.startsWith("--")){
                while (!commande.contains(";")) {
                    commande = commande + "\n" + scanner.nextLine();
                }

                Reader reader = new StringReader(commande);

                Interpreteur analyseur = new Interpreteur(reader);
                analyseur.setBdd(bdd);

                try {
                    analyseur.commande();
                } catch (ParseException e) {
                    System.err.printf("Erreur : %s\n", e.getMessage());
                } catch (SGBDException e) {
                    System.err.printf("Erreur : %s\n", e.getMessage());
                } catch (TokenMgrError e) {
                    System.err.printf("Erreur : %s\n", e.getMessage());
                }
            }
        }
        gestionnaire.sauvegarder(bdd);
        scanner.close();
    }
}