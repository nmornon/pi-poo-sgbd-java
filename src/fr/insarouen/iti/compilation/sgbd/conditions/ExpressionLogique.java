package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.ArrayList;
import java.util.List;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;

/**
 * Classe représentant une expression logique de conditions simples liées par des opérateurs logique
 */
public class ExpressionLogique{

    /** Liste des conditions à évaluer. */
    private List<ConditionSimple> conditions;
    /** Liste des connecteurs entre conditions */
    private List<OperateurLogique> connecteurs;

    /** 
     * Construit une expression logique vide
     */
    public ExpressionLogique(){
        this.conditions= new ArrayList<>();
        this.connecteurs=new ArrayList<>();

    }

    /**
     * Ajoute une condition simple à l'expression logique
     * 
     * @param condition condition à ajouter
     */
    public void ajouterCondition(ConditionSimple condition){
        this.conditions.add(condition);
    }

    /**
     * Ajoute un connecteur à l'expression logique
     * 
     * @param connecteur opérateur logique à ajouter
     */
    public void ajouterConnecteur(OperateurLogique connecteur){
        this.connecteurs.add(connecteur);
    }

    /**
     * Evalue l'expression d'un tuple spécifié
     * 
     * @param tuple le tuple à évaluer
     * @return true si l'expression est vérifiée, false sinon
     */
    public boolean evaluer(Tuple tuple){

        boolean resultat = conditions.get(0).evaluer(tuple);

        for (int i = 1 ; i<conditions.size(); i++){
            boolean courant = conditions.get(i).evaluer(tuple);
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
