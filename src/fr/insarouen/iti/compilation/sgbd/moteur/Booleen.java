package fr.insarouen.iti.compilation.sgbd.moteur;

public class Booleen implements Valeur<Boolean>  {

    private Boolean valeur;

    public Booleen(Boolean valeur) {
        this.valeur = valeur;
    }

    public Boolean getValeur() {
        return this.valeur;
    }
}