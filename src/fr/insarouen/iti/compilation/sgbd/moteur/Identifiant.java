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
    public boolean equals(Object autre) {
        if (autre == null) {
            return false;
        }
        if (autre == this) {
            return true;
        }
        if (!(autre instanceof Identifiant identifiant)) {
            return false;
        }
        return this.getNom().equals(identifiant.getNom());
    }

    @Override
    public int hashCode() {
        return this.getNom().hashCode();
    }
}
