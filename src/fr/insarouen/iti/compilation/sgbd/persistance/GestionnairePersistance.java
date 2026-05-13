package fr.insarouen.iti.compilation.sgbd.persistance;

import java.util.ArrayList;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;

public class GestionnairePersistance {

    public BaseDeDonnees charger(String nomFichier) {
        return new BaseDeDonnees(nomFichier, new ArrayList<>(), 1);
    }
    
    public void sauvegarder(BaseDeDonnees baseDeDonnees) {}
}