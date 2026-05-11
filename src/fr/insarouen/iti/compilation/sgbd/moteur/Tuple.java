package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;

public class Tuple {

    private HashMap<Identifiant, Valeur> valeurs;

    public Tuple() {
        this.valeurs = new HashMap<>();
    }

    public Valeur get(Identifiant id) {
        return this.valeurs.get(id);
    }

    public Tuple set(Identifiant id, Valeur valeur) {
        this.valeurs.put(id, valeur);
        return this;
    }
}