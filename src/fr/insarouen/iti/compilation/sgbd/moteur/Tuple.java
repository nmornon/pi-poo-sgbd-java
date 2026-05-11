package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;
import java.util.Map;

public class Tuple {
    private Map<Identifiant, Valeur> valeurs;

    public Tuple() {
        this.valeurs = new HashMap<>();
    }

    public boolean hasIdentifiant(Identifiant identifiant) {
        return this.valeurs.containsKey(identifiant);
    }

    public Map<Identifiant, Valeur> getValeurs() {
        return this.valeurs;
    }

    public Valeur getValeur(Identifiant identifiant) {
        return this.valeurs.get(identifiant);
    }

    public Tuple setValeur(Identifiant Identifiant, Valeur valeur) {
        this.valeurs.put(Identifiant, valeur);
        return this;
    }
}