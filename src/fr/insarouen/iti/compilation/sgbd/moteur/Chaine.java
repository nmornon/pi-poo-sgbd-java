package fr.insarouen.iti.compilation.sgbd.moteur;

public class Chaine implements Valeur<String>  {

    private String valeur;

    public Chaine(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}