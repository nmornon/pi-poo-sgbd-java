package fr.insarouen.iti.compilation.sgbd.persistance;

import java.io.ObjectOutputStream;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class GestionnairePersistance {

    public GestionnairePersistance() {
    }

    public BaseDeDonnees charger(String nomFichier) throws IOException {
        
        if (!(new File(nomFichier).exists())) {
            BaseDeDonnees baseDeDonnees = new BaseDeDonnees(nomFichier);
            baseDeDonnees.setEstOuverte(true);
            return baseDeDonnees;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))){
            BaseDeDonnees baseDeDonnees = (BaseDeDonnees) ois.readObject();
            baseDeDonnees.setEstOuverte(true);
            return baseDeDonnees;
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de trouver la classe de la base de donnees que vous avez choisi");
            e.printStackTrace();
            return null;
        }
    }
    
    public void sauvegarder(BaseDeDonnees baseDeDonnees) throws IOException{
        FileOutputStream fichier = new FileOutputStream(baseDeDonnees.getIdentifiant());
        ObjectOutputStream oos = new ObjectOutputStream(fichier);
        oos.writeObject(baseDeDonnees);
        oos.close();
    }
}
