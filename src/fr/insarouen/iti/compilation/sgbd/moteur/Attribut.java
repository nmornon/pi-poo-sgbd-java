package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;

public class Attribut {

    private Identifiant id;
    private Class<? extends Valeur<?>> type;

    public Attribut(Identifiant id, Class<? extends Valeur<?>> type) {
        this.id = id;
        this.type = type;
    }
}