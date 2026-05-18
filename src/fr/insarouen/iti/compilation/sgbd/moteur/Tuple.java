package fr.insarouen.iti.compilation.sgbd.moteur;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/**
 * Classe représentant un tuple (ligne) d'une relation
 */
public class Tuple implements Serializable {
    /** Collection de paires de identifiant/valeur */
    private Map<String, Valeur> valeurs;

    /**
     * Construit un tuple vide
     */
    public Tuple() {
        this.valeurs = new HashMap<>();
    }

     /**
     * Indique si le tuple contient l'identifiant spécifié
     * 
     * @param identifiant le nom de la colonne 
     * @return true si le tuple possède cette colonne, false sinon
     */
    public boolean hasIdentifiant(String identifiant) {
        return this.valeurs.containsKey(identifiant);
    }

    /**
     * Renvoie toutes les valeurs du tuple
     *
     * @return une map identifiants/valeurs
     */
    public Map<String, Valeur> getValeurs() {
        return this.valeurs;
    }

    /**
     * Renvoie la valeur associée à l'identifiant spécifié
     *
     * @param identifiant le nom de la colonne
     * @return la valeur associée, ou null si c'est inexistant
     */
    public Valeur getValeur(String identifiant) {
        return this.valeurs.get(identifiant);
    }

    /** 
     * Associe une valeur à un identifiant dans le tuple et le retourne
     * 
     * @param identifiant l'identifiant du tuple
     * @param valeur la valeur associée du tuple
     * @return le tuple
     */
    public Tuple setValeur(String identifiant, Valeur valeur) {
        this.valeurs.put(identifiant, valeur);
        return this;
    }

    /**
     * Préfixe chaque colonne du tuple avec le préfixe spécifié s'il n'est pas
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

    /** Méthode toString() */
    @Override
    public String toString() {
        return valeurs.toString();
    }
}
