package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.HashMap;

public class Tuple {

    private Identifiant id;
    private Class<T extends Valeur<?>> type;

    public Attribut(Identifiant id, Class<T extends Valeur<?>> type) {
        this.id = id;
        this.type = type;
    }
}