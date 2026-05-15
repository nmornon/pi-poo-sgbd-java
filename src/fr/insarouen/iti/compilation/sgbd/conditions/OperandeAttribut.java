package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;

public class OperandeAttribut extends Operande {

    private String nomTableOuAlias;
    private String nomAttribut;

    public OperandeAttribut(String nomTableOuAlias, String nomAttribut){
        this.nomTableOuAlias=nomTableOuAlias;
        this.nomAttribut=nomAttribut;
    }

    @Override
    public Valeur evaluer (Tuple tuple){
        String cleComplete=this.nomTableOuAlias+"."+this.nomAttribut;

        if (tuple.hasIdentifiant(cleComplete)){
            return tuple.getValeur(cleComplete);
        }
        return tuple.getValeur(this.nomAttribut);
    }
}