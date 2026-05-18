package fr.insarouen.iti.compilation.sgbd.moteur;

import java.io.Serializable;

public record Colonne(String identifiant, Type type) implements Serializable {

    /**
     * Préfixe l'colonne avec le préfixe spécifié s'il n'est pas déjà préfixé.
     * 
     * @param prefix le préfix
     * @return l'colonne préfixé
     */
    public Colonne prefixer(String prefix) {
        if (!this.identifiant.contains(".")) {
            String identifiant = String.format(("%s.%s"), prefix, this.identifiant);
            return new Colonne(identifiant, this.type);
        }
        return new Colonne(this.identifiant, this.type);
    }
}