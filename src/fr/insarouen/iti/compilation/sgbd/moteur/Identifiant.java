package fr.insarouen.iti.compilation.sgbd.moteur;

public class Identifiant {
    private String nom;

    public Identifiant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public Boolean equals(Identifiant autre) {
        return this.getNom().equals(autre.getNom());
    }

    @Override
    public boolean hashCode() {
        return this.getNom().hashCode();
    }
}
