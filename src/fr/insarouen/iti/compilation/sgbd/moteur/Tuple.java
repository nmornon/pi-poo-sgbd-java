package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;
import java.util.Map;

public class Tuple {
    private Map<String, Valeur> valeurs;

    public Tuple() {
        this.valeurs = new HashMap<>();
    }

    public boolean hasIdentifiant(String identifiant) {
        return this.valeurs.containsKey(identifiant);
    }

    public Map<String, Valeur> getValeurs() {
        return this.valeurs;
    }

    public Valeur getValeur(String identifiant) {
        return this.valeurs.get(identifiant);
    }

    public Tuple setValeur(String String, Valeur valeur) {
        this.valeurs.put(String, valeur);
        return this;
    }
}