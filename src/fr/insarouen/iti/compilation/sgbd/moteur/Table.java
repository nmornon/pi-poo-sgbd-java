package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.exceptions.InsertionException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;


public class Table extends Relation {
    private BaseDeDonnees baseDeDonnees;

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param colonnes liste d'colonnes décrivant la table
     * 
     * @throws TypeInvalideException
     */
    public Table(
        BaseDeDonnees baseDeDonnees,
        String identifiant,
        Collection<Colonne> colonnes
    ) {
        this(baseDeDonnees, identifiant, colonnes, new ArrayList<>());
    }

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param colonnes liste d'colonnes décrivant la table
     * @param tuples tuples à ajouter à la création de la table
     * 
     * @throws TypeInvalideException
     */
    public Table(
        BaseDeDonnees baseDeDonnees,
        String identifiant,
        Collection<Colonne> colonnes,
        Collection<Tuple> tuples
    ) {
        super(identifiant, colonnes, tuples);
        this.baseDeDonnees = baseDeDonnees;
    }

    /**
     * Insére un tuple dans la table.
     * 
     * @param tuple le tuple à insérer
     */
    public void inserer(Tuple tuple) {
        for (Colonne colonne : this.getColonnes()) {
            if (colonne.type() == Type.SERIAL) {
                if (!tuple.hasIdentifiant(colonne.identifiant())) {
                    tuple.setValeur(colonne.identifiant(), this.baseDeDonnees.getProchaineCle());
                } else {
                    throw new InsertionException("Vous ne pouvez pas spécifiée une valeur de type SERIAL.");
                }
            }
        }
        this.verifierTuple(tuple);
        this.getTuples().add(tuple);
    }

    /**
     * Supprime tous les tuples de la table.
     * 
     * @return le nombre de tuples qui ont été supprimés
     */
    public int supprimer() {
        int nbLigneSupprimes = this.getTuples().size();
        this.getTuples().clear();
        return nbLigneSupprimes;
    }

    /**
     * Supprimer les tuples de la table selon l'expression logique spécifiée.
     * 
     * @param condition la condition à appliquer sur les tuples à supprimé
     * @return le nombre de tuples supprimés
     */
    public int supprimer(ExpressionLogique condition) {
        Iterator<Tuple> iterator = this.getTuples().iterator();
        int nbTuplesSupprimes = 0;

        while (iterator.hasNext()) {
            if (condition.evaluer(iterator.next())) {
                iterator.remove();
                nbTuplesSupprimes++;
            }
        }
        return nbTuplesSupprimes;
    }
}