package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/** Classe représentant les opérandes */
public abstract class Operande{
    
    public abstract Valeur evaluer(Tuple tuple);
}