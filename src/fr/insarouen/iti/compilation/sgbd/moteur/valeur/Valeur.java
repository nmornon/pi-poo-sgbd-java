package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.moteur.Type;

public abstract class Valeur implements Comparable<Valeur> {
    private Type type;
    private Object valeur;

    public Valeur(Type type, Object valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    public Type getType() {
        return this.type;
    }
    
    public Object getValeur() {
        return this.valeur;
    }

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

    @Override
    public String toString(){
        return String.format(
            "%s(%s)",
            this.getClass().getSimpleName(),
            this.valeur
        );
    }
}