package fr.insarouen.iti.compilation.sgbd.moteur;

public class Identifiant {

    private String nom;

    public Identifiant(String nom) {
        this.nom = nom;
    }

    private String getNom() {
        return this.nom;
    }

    public boolean equals(Object obj) {
        
        if (!(obj instanceof Identifiant)){
            return false;
        }

        Identifiant autre =(Identifiant) obj;
        return this.nom.equals(autre.nom);
    }

    public int hashCode(){
        return this.nom.hashCode();
    }
}