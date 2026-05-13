package fr.insarouen.iti.compilation.sgbd.persistance;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Collection;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.moteur.Table;

public class GestionnairePersistance {

    public GestionnairePersistance() {
    }

    public BaseDeDonnees charger(String nomFichier) throws IOException{
        FileInputStream fichier = new FileInputStream(nomFichier);
        ObjectInputStream ois = new ObjectInputStream(fichier);
        Collection<Table> tabs = new ArrayList<>();
        BaseDeDonnees baseDeDonnees = new BaseDeDonnees(nomFichier,tabs,0);
        try {
            baseDeDonnees = (BaseDeDonnees) ois.readObject();
            baseDeDonnees.setEstOuverte(true);
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de trouver la classe de la base de donnees que vous avez choisi");
            e.printStackTrace();
        }
        return baseDeDonnees;
    }
    
    public void sauvegarder(BaseDeDonnees baseDeDonnees) throws IOException{
        FileOutputStream fichier = new FileOutputStream(baseDeDonnees.getIdentifiant());
        ObjectOutputStream oos = new ObjectOutputStream(fichier);
        oos.writeObject(baseDeDonnees);
    }
}