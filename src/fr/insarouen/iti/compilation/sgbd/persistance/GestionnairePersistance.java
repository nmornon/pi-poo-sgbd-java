package fr.insarouen.iti.compilation.sgbd.persistance;

import java.io.ObjectOutputStream;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/** Classe GestionnairePersistance permettant les sauvegardes de base de données sur un fichier */
public class GestionnairePersistance {

    /**
     * Construit un gestionnaire de persistance.
     */
    public GestionnairePersistance() {
    }

    /**
     * Charge la base de données à partir d'un fichier
     * 
     * @param nomFichier le nom du fichier pour charger la base
     * @return la base chargée
     * @throws IOException si on n'arrive pas à lire le fichier
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
    
    /** Sauvegarde la base dans un fichier
     * 
     * @param baseDeDonnees base à sauvegarder
     * @throws IOException si on n'arrive pas à écrire dans le fichier
     */
    public void sauvegarder(BaseDeDonnees baseDeDonnees) throws IOException{
        FileOutputStream fichier = new FileOutputStream(baseDeDonnees.getIdentifiant());
        ObjectOutputStream oos = new ObjectOutputStream(fichier);
        oos.writeObject(baseDeDonnees);
        oos.close();
    }
}