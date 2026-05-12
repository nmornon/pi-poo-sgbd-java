package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;

public abstract class Operande{
    
    public abstract Valeur evaluer(Map<String,Tuple> contexte);
}