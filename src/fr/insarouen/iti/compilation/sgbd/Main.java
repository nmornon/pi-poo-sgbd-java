package fr.insarouen.iti.compilation.sgbd;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.insarouen.iti.compilation.sgbd.analyseurs.Interpreteur;
import fr.insarouen.iti.compilation.sgbd.analyseurs.TokenMgrError;
import fr.insarouen.iti.compilation.sgbd.analyseurs.ParseException;
import fr.insarouen.iti.compilation.sgbd.exceptions.SGBDException;
import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.persistance.GestionnairePersistance;

public class Main {
    private static Interpreteur interpreteur = null;
    
    public static void main(String[] args) throws IOException {
        GestionnairePersistance gestionnaire = new GestionnairePersistance();
        BaseDeDonnees bdd = gestionnaire.charger(args[0]);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vous êtes connectés à...");
        figlet(bdd.getIdentifiant());
        // System.out.printf("Base de données : %s\n", bdd.getIdentifiant());

        while (bdd.getEstOuverte()) {
            System.out.print("> ");
            String commande = scanner.nextLine();

            if (!commande.startsWith("--")){
                while (!commande.contains(";")) {
                    commande = commande + "\n" + scanner.nextLine();
                }

            initialiserInterpreteur(commande);

            try {
                Interpreteur.commande(bdd);
            } catch (ParseException e) {
                System.err.printf("Erreur : %s\n", e.getMessage());
            } catch (SGBDException e) {
                System.err.printf("Erreur : %s\n", e.getMessage());
            } catch (TokenMgrError e) {
                System.err.printf("Erreur : %s\n", e.getMessage());
            }

            }
            gestionnaire.sauvegarder(bdd);
            scanner.close();
        }
    }

    private static void initialiserInterpreteur(String commande) {
        Reader reader = new StringReader(commande);

        if (Main.interpreteur == null) {
            Main.interpreteur = new Interpreteur(reader);
        } else {
            Interpreteur.ReInit(reader);
        }
    }

    private static void figlet(String cmdline) throws IOException {
        Process process = 
            new ProcessBuilder(new String[] {"bash", "-c", String.format("figlet '%s'",cmdline)})
                // .redirectErrorStream(true)
                .directory(new File("."))
                .start();

        ArrayList<String> output = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null)
            System.out.println(line);            
    }
}