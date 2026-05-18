package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.moteur.Type;

/** Classe abstraites représentant les différentes valeurs possibles pour les tuples et colonnes */
public abstract class Valeur implements Comparable<Valeur> {
    /** Le type */
    private Type type;
    /** La valeur */
    private Object valeur;

    /** Construit la valeur typée
     * 
     * @param type type pris par la valeur
     * @param valeur valeur possible
     */
    public Valeur(Type type, Object valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    /** Renvoie le type de la valeur
     * 
     * @return le type associée à cette valeur
     */
    public Type getType() {
        return this.type;
    }
    
    /** Renvoie la valeur
     * 
     * @return l'objet représentant la valeur
     */
    public Object getValeur() {
        return this.valeur;
    }

    /** Compare deux valeurs
     * 
     * @param object l'objet à comparer
     * @return true si elles ont le même type et la même valeur, false sinon
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Valeur valeur)) {
            return false;
        }
        return this.type == valeur.type
            && this.valeur.equals(valeur.valeur);
    }

    /** Méthode toString() */
    @Override
    public String toString(){
        return String.format(
            "%s(%s)",
            this.getClass().getSimpleName(),
            this.valeur
        );
    }
}