package fr.insarouen.iti.compilation.sgbd.moteur;

class Identifiant {
    private String nom;

    public Identifiant(String nom){
        this.nom = nom
    }

    public String getNom(){
        return this.nom
    }

    public boolean equals(Identifiant identifiant){
        return this.getNom() == identifiant.getNom();
    }
}