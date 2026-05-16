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
        Valeur valeurGauche = this.gauche.evaluer(tuple);
        Valeur valeurDroite = this.droite.evaluer(tuple);
        int evaluation = valeurGauche.compareTo(valeurDroite);

        return switch (this.comparateur) {
            case EGAL      -> evaluation ==  0;
            case SUPERIEUR -> evaluation == +1;
            case INFERIEUR -> evaluation == -1;
            default        -> false;
        };
    }
}
