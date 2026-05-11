package fr.insarouen.iti.compilation.sgbd.moteur;

import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;


class Table extends Relation {
    private Identifiant identifiant;

    /**
     * Constructeur de la classe Table
     * @param identifiant identifiant de la table
     * @param attributs liste d'attributs décrivant la table
     * @param tuples tuples à ajouter à la création de la table
     * 
     * @throws TypeInvalideException
     */
    public Table(Identifiant identifiant, List<Attribut> attributs, List<Tuple> tuples) throws TypeInvalideException {
        this.identifiant = identifiant;
        super(attributs, tuples);
    }

    public Identifiant getIdentifiant(){
        return this.identifiant;
    }

    /**
     * Méthode permettant d'insérer un seul tuple dans la table
     * @param tuple tuple à insérer
     */
    public void inserer(Tuple tuple) throws TypeInvalideException {
        this.verifierTuple(tuple);
        this.getTuples().add(tuple);

    }

    /**
     * Méthode permettant d'insérer plusieurs tuples en une fois dans la table
     * 
     * On vérifie d'abord si tous les tuples sont valides, ensuite si il n'y a aucune erreur
     * on les rajoute tous
     * 
     * @param tuples tableau de tuples
     */
    public void inserer(List<Tuple> tuples) throws TypeInvalideException {
        for(Tuple tuple : tuples){
            this.verifierTuple(tuple);
        }
        for(Tuple tuple : tuples){
            this.getTuples().add(tuple);
        }
    }

    /**
     * Méthode permettant de supprimer des tuples de la table selon une expression logique
     * @param condition condition à appliquer pour supprimer certains tuples (~filtre)
     * @return le nombre de tuples qui a été supprimé avec la condition
     */
    public int supprimer(ExpressionLogique condition){
        // this.getTuples().delete()
        return 0;
    }


}