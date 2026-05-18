package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/**
 * Classe représentant une condition de la forme : membre gauche - comparateur - membre droit
 */
public class ConditionSimple {
    /** Opérande gauche */
    private Operande gauche;
    /** Opérande droite */
    private Operande droite;
    /** Type de comparaison */
    private Comparateur comparateur;

    /**
     * Construit une condition simple à partir de deux opérandes et d'un comparateur
     * 
     * @param gauche opérande gauche
     * @param droite opérande droite
     * @param comparateur opérateur de comparaison
     */
    public ConditionSimple(Operande gauche, Operande droite, Comparateur comparateur) {
        this.gauche = gauche;
        this.droite = droite;
        this.comparateur = comparateur;
    }

    /** Evalue la condition d'un tuple spécifié
     * 
     * @param tuple le tuple à évaluer
     * @return true si la comparaison est vérifiée, false sinon
     */
    public boolean evaluer(Tuple tuple) {
        Valeur valeurGauche = this.gauche.evaluer(tuple);
        Valeur valeurDroite = this.droite.evaluer(tuple);
        int evaluation = valeurGauche.compareTo(valeurDroite);

        return switch (this.comparateur) {
            case EGAL      -> evaluation == 0;
            case SUPERIEUR -> evaluation  > 0;
            case INFERIEUR -> evaluation  < 0;
            default        -> false;
        };
    }
}
