package fr.insarouen.iti.compilation.sgbd.moteur;

public class Cle extends Valeur {
    public Cle(int valeur) {
        super(Type.SERIAL, valeur);
    }
}