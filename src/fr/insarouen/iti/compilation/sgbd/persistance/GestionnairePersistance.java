package fr.insarouen.iti.compilation.sgbd.persistance;

import java.io.ObjectOutputStream;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class GestionnairePersistance {

    public GestionnairePersistance() {
    }

    /**
     * Charge une base de données depuis un fichier 
     * @param nomFichier nom du fichier
     * 
     * @throws IOException
     */
    public BaseDeDonnees charger(String nomFichier) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))){
            BaseDeDonnees baseDeDonnees = (BaseDeDonnees) ois.readObject();
            baseDeDonnees.setEstOuverte(true);
            return baseDeDonnees;
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de trouver la classe de la base de donnees que vous avez choisi");
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Il n'y a pas de fichier de ce nom");
        }
    }
    
    /**
     * Sauvegarde une base de données dans un fichier 
     * @param baseDeDonnnees la base de données a sauvegarder
     * 
     */
    public void sauvegarder(BaseDeDonnees baseDeDonnees) throws IOException{
        FileOutputStream fichier = new FileOutputStream(baseDeDonnees.getIdentifiant());
        ObjectOutputStream oos = new ObjectOutputStream(fichier);
        oos.writeObject(baseDeDonnees);
        oos.close();
    }
}