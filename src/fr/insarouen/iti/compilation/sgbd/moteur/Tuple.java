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

    /**
     * Préfixe chaque attribut du tuple avec le préfixe spécifié s'il n'est pas
     * déjà préfixé.
     * 
     * @param prefix le préfix
     * @return le tuple préfixé
     */
    public Tuple prefixer(String prefix) {
        Tuple tuple = new Tuple();
        
        for (Map.Entry<String, Valeur> entry : this.valeurs.entrySet()) {
            String identifiant=entry.getKey();
            
            if (!identifiant.contains(".")) {
                identifiant=prefix+"."+identifiant;
            }
            tuple.setValeur(identifiant, entry.getValue());
        }
        return tuple;
    }

    /**
     * Renvoie un tuple contenant la concaténation des deux tuples spécifiés.
     * 
     * @param tuple1 le premier tuple à concaténer
     * @param tuple2 le second tuple à concaténer
     * @return le tuple concaténé
     */
    public Tuple concatener(Tuple tuple) {
        Tuple concatenation = new Tuple();

        for (Map.Entry<String, Valeur> entry : this.valeurs.entrySet()) {
            concatenation.setValeur(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Valeur> entry : tuple.valeurs.entrySet()) {
            concatenation.setValeur(entry.getKey(), entry.getValue());
        }
        return concatenation;
    }

    @Override
    public String toString() {
        return valeurs.toString();
    }
}
