package fr.insarouen.iti.compilation.sgbd.moteur;

import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Table extends Relation {
    private BaseDeDonnees baseDeDonnees;

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param attributs liste d'attributs décrivant la table
     * 
     * @throws TypeInvalideException
     */
    public Table(
        BaseDeDonnees baseDeDonnees,
        String identifiant,
        Collection<Attribut> attributs
    ) {
        this(baseDeDonnees, identifiant, attributs, new ArrayList<>());
    }

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param attributs liste d'attributs décrivant la table
     * @param tuples tuples à ajouter à la création de la table
     * 
     * @throws TypeInvalideException
     */
    public Table(
        BaseDeDonnees baseDeDonnees,
        String identifiant,
        Collection<Attribut> attributs,
        Collection<Tuple> tuples
    ) {
        super(identifiant, attributs, tuples);
        this.baseDeDonnees = baseDeDonnees;
    }

    /**
     * Insére un tuple dans la table.
     * 
     * @param tuple le tuple à insérer
     */
    public void inserer(Tuple tuple) {
        for (Attribut attribut : this.getAttributs()) {
            if (attribut.type() == Type.SERIAL) {
                if (!tuple.hasIdentifiant(attribut.identifiant())) {
                    tuple.setValeur(attribut.identifiant(), this.baseDeDonnees.getProchaineCle());
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

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Table table)) {
            return false;
        }
        return this.baseDeDonnees == table.baseDeDonnees
            && this.getIdentifiant().equals(table.getIdentifiant())
            && this.getAttributs().equals(table.getAttributs())
            && this.getTuples().equals(table.getTuples());
    }
}