package fr.insarouen.iti.compilation.sgbd.moteur;

public class Chaine extends Valeur {
    public Chaine(String valeur) {
        super(Type.VARCHAR, valeur);
    }
}