package fr.insarouen.iti.compilation.sgbd.moteur;

public class Cle implements Valeur {

    private Number valeur;
    private Type type;

    public Cle(Number valeur) {
        this.valeur = valeur;
        this.type = Type.SERIAL;
    }

    public Number getValeur() {
        return this.valeur;
    }

    public Type getType() {
        return this.type;
    }
}