package fr.insarouen.iti.compilation.sgbd.moteur;

import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import java.util.Collection;


class Table extends Relation {

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param attributs liste d'attributs décrivant la table
     * 
     * @throws TypeInvalideException
     */
    public Table(String identifiant, Collection<Attribut> attributs) {
        super(identifiant, attributs);
    }

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param attributs liste d'attributs décrivant la table
     * @param tuples tuples à ajouter à la création de la table
     * 
     * @throws TypeInvalideException
     */
    public Table(String identifiant, Collection<Attribut> attributs, Collection<Tuple> tuples) {
        super(identifiant, attributs, tuples);
    }

    /**
     * Méthode permettant d'insérer plusieurs tuples en une fois dans la table
     * 
     * On vérifie d'abord si tous les tuples sont valides, ensuite si il n'y a aucune erreur
     * on les rajoute tous
     * 
     * @param tuples tableau de tuples
     */
    public void inserer(Collection<Tuple> tuples) throws TypeInvalideException {
        for(Tuple tuple : tuples){
            this.verifierTuple(tuple);
        }
        for(Tuple tuple : tuples){
            this.getTuples().add(tuple);
        }
    }

    /**
     * Supprime tous les tuple de la relation
     * 
     * @return le nombre de tuples qui ont été supprimés
     */
    public int supprimer() {
        int nbLigneSupprimes = this.getTuples().size();
        this.getTuples().clear();
        return nbLigneSupprimes;
    }

    /**
     * Méthode permettant de supprimer des tuples de la table selon une expression logique
     * 
     * @param condition condition à appliquer pour supprimer certains tuples (~filtre)
     * @return le nombre de tuples qui a été supprimé avec la condition
     */
    public int supprimer(ExpressionLogique condition) {
        // this.getTuples().delete()
        return 0;
    }
}