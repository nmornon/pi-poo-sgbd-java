package fr.insarouen.iti.compilation.sgbd.moteur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;

/**
 * Classe représentant une relation.
 */
class Relation {
    /** La liste des attriuts de la relation */
    private Collection<Attribut> attributs;
    /** La liste des tuples de la relation */
    private final Collection<Tuple> tuples = new ArrayList<>();

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
    public Relation(Collection<Attribut> attributs, Collection<Tuple> tuples) throws TypeInvalideException {
        this.attributs = attributs;

        for (Tuple tuple : tuples) {
            this.verifierTuple(tuple);
            this.tuples.add(tuple);
        }
    }

    public boolean hasAttribut(Identifiant identifiantAttribut) {
        for (Attribut attribut : this.getAttributs()) {
            if (attribut.getIdentifiant().equals(identifiantAttribut)) {
                return true;
            }
        }
        return false;
    }

    public boolean getAttribut(Identifiant identifiantAttribut) {
        for (Attribut attribut : this.getAttributs()) {
            if (attribut.getIdentifiant().equals(identifiantAttribut)) {
                return attribut;
            }
        }
        return null;
    }

    public Collection<Attribut> getAttributs() {
        return this.attributs;
    }

    public Collection<Tuple> getTuples() {
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
        for (Attribut attribut : this.getAttributs()) {
            if (tuple.hasIdentifiant(attribut.getIdentifiant())) {
                Valeur valeur = tuple.get(attribut.getIdentifiant());

                if (attribut.getType() != valeur.getType()) {
                    throw new TypeInvalideException("Mauvais type");
                }
            } else if (attribut.getType() != Type.SERIAL) {
                throw new TypeInvalideException("Attribut inexistant");
            }
        }
    }

    /**
     * Applique une sélection sur la relation à partir des attributs spécifiés.
     * 
     * @param attributs la liste attributs à sélectionner
     * @return la nouvelle relation
     */
    public Relation selectionner(Collection<Attribut> attributs) {
        Collection<Tuple> tuples = new ArrayList<>();

        for (Tuple tuple : this.getTuples()) {
            Tuple nouveauTuple = new Tuple();

            for (Attribut attribut : attributs) {
                nouveauTuple.set(attribut.getIdentifiant(), tuple.get(attribut.getIdentifiant()));
            }
            tuples.add(nouveauTuple);
        }
        return new Relation(attributs, tuples);
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
        Collection<Tuple> tuples = new ArrayList<>();
        Collection<Attribut> attributs = this.concatenerAttributs(this.getAttributs(), relation.getAttributs());

        for (Tuple tuple1 : this.getTuples()) {
            for (Tuple tuple2 : relation.getTuples()) {
                tuples.add(this.concatenerTuple(tuple1, tuple2));
            }
        }
        return new Relation(attributs, tuples);
    }

    private Collection<Tuple> concatenerTuples(Tuples tuple1, Tuple tuple2) {
        Tuple tuple = new Tuple();

        for (Map.Entry entry : tuple1.getValeurs().entrySet()) {
            tuple.setValeur(entry.getKey(), entry.getType());
        }
        for (Map.Entry entry : tuple2.getValeurs().entrySet()) {
            tuple.setValeur(entry.getKey(), entry.getType());
        }
        return tuple;
    }

    private Collection<Attribut> concatenerAttribut(
        Collection<Attribut> attributs1,
        Collection<Attribut> attributs2
    ) {
        Collection<Attribut> attributs = new ArrayList<>();

        for (Attribut attribut : attributs1) {
            attributs.add(attribut);
        }
        for (Attribut attribut : attributs2) {
            attributs.add(attribut);
        }
        return attributs;
    }
}