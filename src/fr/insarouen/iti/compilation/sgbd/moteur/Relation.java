package fr.insarouen.iti.compilation.sgbd.moteur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;

/**
 * Classe représentant une relation.
 */
class Relation {
    /** La liste des attriuts de la relation */
    private List<Attribut> attributs;
    /** La liste des tuples de la relation */
    private final List<Tuple> tuples = new ArrayList<>();

    /**
     * Construit une relation à partir de la liste d'attributs spécifiée et de
     * la liste de tuples spécifiée.
     * 
     * @param attributs la liste des attributs
     * @param tuples    la liste des tuple
     * 
     * @throws TypeInvalideException si au moins un tuple ne correspond pas aux
     *                       attributs
     */
    public Relation(List<Attribut> attributs, List<Tuple> tuples) throws TypeInvalideException {
        this.attributs = attributs;

        for (Tuple tuple : tuples) {
            this.verifierTuple(tuple);
            this.tuples.add(tuple);
        }
    }

    public List<Attribut> getAttributs() {
        return this.attributs;
    }

    public List<Tuple> getTuples() {
        return this.tuples;
    }

    /**
     * Vérifie si le tuple spécifiée est valide, lève TypeInvalideException si
     * non.
     * 
     * @param tuple le tuple à vérifier
     * 
     * @throws TypeInvalideException si le tuple spécifié est invalide
     */
    protected void verifierTuple(Tuple tuple) throws TypeInvalideException {
    }

    /**
     * Applique une sélection sur la relation à partir des attributs spécifiés.
     * 
     * @param attributs la liste attributs à sélectionner
     * @return la nouvelle relation
     */
    public Relation selectionner(List<Attribut> attributs) {
        return null;
    }

    /**
     * Applique une projection sur la relation à partir de l'expression logique
     * spécifiée.
     * 
     * @param expression l'expression logique
     * @return la nouvelle relation
     */
    public Relation projeter(ExpressionLogique expression) {
        return null;
    }

    /**
     * Applique une jointure sur la relation à partir d'une seconde relation,
     * et de deux attributs appartenant chacun à une relation.
     * 
     * @param relation  la relation à joindre
     * @param attribut1 l'attribut de la première relation à joindre
     * @param attribut2 l'attribut de la seconde relation à joindre
     * @return la nouvelle relation
     */
    public Relation joindre(Relation relation, Attribut attribut1, Attribut attribut2) {
    }

    /**
     * Applique un produit cartésien sur la relation à partir d'une seconde
     * relation.
     * 
     * @param relation la relation
     * @return la nouvelle relation
     */
    public Relation produitCartesien(Relation relation) {
        return null;
    }
}