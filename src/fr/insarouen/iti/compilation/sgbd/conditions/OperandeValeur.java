package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;
import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;

public class OperandeValeur extends Operande {

    private Valeur valeur;

    public OperandeValeur(Valeur valeur){
        this.valeur=valeur;
    }

    @Override
    public Valeur evaluer (Map<Identifiant,Tuple> contexte){
        return valeur;
    }
}