package fr.insarouen.iti.compilation.sgbd.moteur;

public class Cle implements Valeur<Number>  {

    private Number valeur;

    public Cle(Number valeur) {
        this.valeur = valeur;
    }

    public Number getValeur() {
        return this.valeur;
    }
}