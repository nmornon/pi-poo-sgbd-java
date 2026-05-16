package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

public class ConditionSimple {
    private Operande gauche;
    private Operande droite;
    private Comparateur comparateur;

    public ConditionSimple(Operande gauche, Operande droite, Comparateur comparateur) {
        this.gauche = gauche;
        this.droite = droite;
        this.comparateur = comparateur;
    }

    public boolean evaluer(Tuple tuple) {
        Valeur valeurGauche = gauche.evaluer(tuple);
        Valeur valeurDroite = droite.evaluer(tuple);

        int comparaison = valeurGauche.compareTo(valeurDroite);

        switch (comparateur){
            case EGAL:      return comparaison ==  0;
            case SUPERIEUR: return comparaison == +1;
            case INFERIEUR: return comparaison == -1;
            default:        return false;
        }
    }
}
