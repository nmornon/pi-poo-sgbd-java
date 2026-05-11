package fr.insarouen.iti.compilation.sgbd.moteur;

public class Identifiant {

    private String nom;

    public Identifiant(String nom) {
        this.nom = nom;
    }

    private String getNom() {
        return this.nom;
    }

    public Boolean equals(Identifiant autre) {
        return this.getNom().equals(autre.getNom());
    }
}