package fr.insarouen.iti.compilation.sgbd.moteur;

import java.io.Serializable;

public record Colonne(String identifiant, Type type) implements Serializable {

    /**
     * Préfixe la colonne avec le préfixe spécifié s'il n'est pas déjà préfixé.
     * 
     * @param prefix le préfix
     * @return la colonne préfixé
     */
    public Colonne prefixer(String prefix) {
        if (!this.identifiant.contains(".")) {
            String identifiant = String.format(("%s.%s"), prefix, this.identifiant);
            return new Colonne(identifiant, this.type);
        }
        return new Colonne(this.identifiant, this.type);
    }

    /** Compare deux colonnes à l'aide de leur identifiant
     * 
     * @param object objet à comparer avec la colonne
     * @return true si les identifiants sont les mêmes, false sinon
     */
    @Override
    public boolean equals(Object object){
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Colonne colonne)){
            return false;
        } 
        return this.identifiant.equals(colonne.identifiant);
    }

    /**
     * Renvoie le hashcode de la colonne
     * @return le code de hashage de la colonne
     */
    @Override
    public int hashCode() {
        return this.identifiant.hashCode();
    }
}