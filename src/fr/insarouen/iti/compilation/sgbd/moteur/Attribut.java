package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;

public class Attribut {

    private Identifiant id;
    private Type type;

    public Attribut(Identifiant id, Type type) {
        this.id = id;
        this.type = type;
    }
}