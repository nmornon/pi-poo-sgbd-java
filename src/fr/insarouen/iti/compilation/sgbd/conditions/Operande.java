package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;

public abstract class Operande{
    
    public abstract Valeur evaluer(Tuple tuple);
}