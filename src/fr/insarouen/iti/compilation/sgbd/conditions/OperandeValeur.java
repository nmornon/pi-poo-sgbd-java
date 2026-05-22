package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/** Classe représentant un opérande correspondant à une valeur */
public class OperandeValeur extends Operande {
    /** Valeur */
    private Valeur valeur;

    /**
     * Construit un opérande à partir d'une valeur
     *
     * @param valeur valeur
     */
    public OperandeValeur(Valeur valeur){
        this.valeur=valeur;
    }

    /**
     * Évalue l'opérande d'un tuple spécifié
     *
     * @param tuple tuple à évaluer
     * @return la valeur de l'opérande
     */
    @Override
    public Valeur evaluer (Tuple tuple){
        return this.valeur;
    }
}