package fr.insarouen.iti.compilation.sgbd.moteur;

public class Chaine extends Valeur {

    private Type type;

    public Chaine(String valeur) {
        super(valeur);
        this.type = Type.VARCHAR;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.valeur;
    }
}