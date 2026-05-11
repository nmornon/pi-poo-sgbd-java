package fr.insarouen.iti.compilation.sgbd.moteur;

public class Nombre implements Valeur<Number>  {

    private Number valeur;

    public Nombre(Number valeur) {
        this.valeur = valeur;
    }

    public Number getValeur() {
        return this.valeur;
    }
}