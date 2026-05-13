package fr.insarouen.iti.compilation.sgbd.moteur;

public class Cle extends Valeur {

    private Type type;

    public Cle(Number valeur) {
        super(valeur);
        this.type = Type.SERIAL;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString(){
        return this.valeur.toString();
    }
}