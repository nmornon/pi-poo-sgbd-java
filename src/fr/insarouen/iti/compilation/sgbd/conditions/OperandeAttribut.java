package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;
import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;

public class OperandeAttribut extends Operande {

    private Identifiant nomTableOuAlias;
    private Identifiant nomAttribut;

    public OperandeAttribut(Identifiant nomTableOuAlias,Identifiant nomAttribut){
        this.nomTableOuAlias=nomTableOuAlias;
        this.nomAttribut=nomAttribut;
    }

    @Override
    public Valeur evaluer (Map<Identifiant,Tuple> contexte){
        
        Tuple tuple=contexte.get(this.nomTableOuAlias);
        return tuple.get(this.nomAttribut);
    }
}