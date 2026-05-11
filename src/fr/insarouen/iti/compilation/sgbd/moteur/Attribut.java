package fr.insarouen.iti.compilation.sgbd.moteur;

public class Attribut {
    private Identifiant identifiant;
    private Type type;

    public Attribut(Identifiant identifiant, Type type) {
        this.identifiant = identifiant;
        this.type = type;
    }

    public Identifiant getIdentifiant() {
        return this.identifiant;
    }

    public Type getType() {
        return this.type;
    }
}
