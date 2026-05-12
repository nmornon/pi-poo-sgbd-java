package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;
import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;

public class ConditionSimple {

    private Operande gauche;
    private Operande droite;
    private Comparateur comparateur;

    public ConditionSimple(Operande gauche,Operande droite , Comparateur comparateur){
        this.gauche=gauche;
        this.droite=droite;
        this.comparateur=comparateur;
    }

    public boolean evaluer ( Map<Identifiant,Tuple> contexte ){

        Valeur valeurGauche = gauche.evaluer(contexte);
        Valeur valeurDroite =droite.evaluer(contexte);

        Object gauche= valeurGauche.getValeur();
        Object droite = valeurDroite.getValeur();

        switch(comparateur){

            case EGAL :
                return gauche.equals(droite);

            case SUPERIEUR : return ((Integer) gauche)>((Integer)droite);

            case INFERIEUR : return ((Integer) gauche)<((Integer)droite);

            default: return false;
        }
    }



}