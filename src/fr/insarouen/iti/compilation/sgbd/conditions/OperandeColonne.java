package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneInexistantException;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/** Classe représentant un opérande correspondant à une colonne */
public class OperandeColonne extends Operande {
    /** Identifiant la colonne */
    private String identifiant;

    /**
     * Construit un opérande à partir d'un préfixe et d'un nom d'attribut
     *
     * @param prefix préfixe de colonne
     * @param attribut nom de l'attribut
     */
    public OperandeColonne(String prefix, String attribut) {
        this(String.format("%s.%s", prefix, attribut));
    }

    /**
     * Construit un opérande à partir d'un identifiant de colonne
     *
     * @param identifiant identifiant de la colonne
     */
    public OperandeColonne(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Évalue l'opérande d'un tuple spécifié
     *
     * @param tuple tuple à évaluer
     * @return valeur associée à l'identifiant de la colonne
     * @throws ColonneInexistantException si la colonne n'existe pas
     */
    @Override
    public Valeur evaluer(Tuple tuple) {
        if (!tuple.hasIdentifiant(this.identifiant)) {
            throw new ColonneInexistantException(String.format(
                "Attribut '%s' inexistant dans la relation", this.identifiant));
        }
        return tuple.getValeur(this.identifiant);
    }
}
