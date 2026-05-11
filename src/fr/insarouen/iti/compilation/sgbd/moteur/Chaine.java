package fr.insarouen.iti.compilation.sgbd.moteur;

public class Chaine implements Valeur {

    private String valeur;
    private Type type;

    public Chaine(String valeur) {
        this.valeur = valeur;
        this.type = Type.VARCHAR;
    }

    public String getValeur() {
        return this.valeur;
    }

    public Type getType() {
        return this.type;
    }
}