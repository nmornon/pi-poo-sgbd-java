package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;

public class OperandeValeur extends Operande {

    private Valeur valeur;

    public OperandeValeur(Valeur valeur){
        this.valeur=valeur;
    }

    @Override
    public Valeur evaluer (Tuple tuple){
        return this.valeur;
    }
}