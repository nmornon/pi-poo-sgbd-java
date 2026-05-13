package fr.insarouen.iti.compilation.sgbd.moteur;

public class Nombre extends Valeur {

    private Type type;
    
    public Nombre(Number valeur) {
        super(valeur);
        this.type = Type.INT;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString(){
        return this.valeur.toString();
    }
}