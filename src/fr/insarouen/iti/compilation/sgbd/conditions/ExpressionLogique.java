package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;
import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;

public class ExpressionLogique{

    private List<ConditionSimple> conditions;
    private List<OperateurLogique> connecteurs;

    public ExpressionLogique(){
        this.conditions= new ArrayList<>();
        this.connecteurs=new ArrayList<>();

    }

    public void ajouterCondition(ConditionSimple condition){
        this.conditions.add(condition);
    }

    public void ajouterConnecteur(OperateurLogique connecteur){
        this.connecteurs.add(connecteur);
    }

    public boolean evaluer(Map<Identifiant,Tuple> contexte){

        boolean resultat = conditions.get(0).evaluer(contexte);

        for (int i = 1 ; i<conditions.size(); i++){
            boolean courant = conditions.get(i).evaluer(contexte);
            OperateurLogique connecteur=connecteurs.get(i-1);

            if ( connecteur==OperateurLogique.AND){
                resultat = resultat && courant;
            } else{
                resultat = resultat || courant;
            }
        }

        return resultat; 
    }


}
