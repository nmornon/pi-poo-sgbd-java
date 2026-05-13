package fr.insarouen.iti.compilation.sgbd.moteur;

public abstract class Valeur {

    private Object valeur;

    public Valeur(Object valeur) {
        this.valeur = valeur;
    }
    public Object getValeur() {
        return this.valeur;
    }

    public abstract Type getType();

    public boolean equals(Valeur autre) {
        return (this.getType() == autre.getType() && this.getValeur() == autre.getValeur());
    }
}