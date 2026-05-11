package fr.insarouen.iti.compilation.sgbd.moteur;

public class Nombre implements Valeur {

    private Number valeur;
    private Type type;

    public Nombre(Number valeur) {
        this.valeur = valeur;
        this.type = Type.INT;
    }

    public Number getValeur() {
        return this.valeur;
    }

    public Type getType() {
        return this.type;
    }
}