package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.insarouen.iti.compilation.sgbd.exceptions.AttributDejaExistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.AttributInexistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.ProduitCartesienException;
import fr.insarouen.iti.compilation.sgbd.exceptions.RelationSansAttributException;
import fr.insarouen.iti.compilation.sgbd.exceptions.SelectionException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;

/**
 * Classe représentant une relation.
 */
class Relation {
    /** L'identifiant de la relation */
    private String identifiant;
    /** La liste des attributs de la relation */
    private Set<Attribut> attributs = new HashSet<>();
    /** La liste des tuples de la relation */
    private Collection<Tuple> tuples = new ArrayList<>();

    /**
     * Construit une relation à partir de la liste d'attributs spécifiée.
     * 
     * @param attributs la liste des attributs
     */
    public Relation(String identifiant, Collection<Attribut> attributs) {
        this(identifiant, attributs, new ArrayList<>());
    }

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
    public Relation(String identifiant, Collection<Attribut> attributs, Collection<Tuple> tuples) {
        this.identifiant = identifiant;

        if (attributs.isEmpty()) {
            throw new RelationSansAttributException("Une relation doit avoir au moins un attribut");
        }

        for (Attribut attribut : attributs) {
            if (this.hasAttribut(attribut.identifiant())) {
                throw new AttributDejaExistantException("Deux attributs ne peuvent pas avoir le même identifiant");
            }
            this.attributs.add(attribut);
        }
	
        for (Tuple tuple : tuples) {
            this.verifierTuple(tuple);
            this.tuples.add(tuple);
        }
    }

    public String getIdentifiant(){
        return this.identifiant;
    }

    /**
     * Détermine si la relation a un attribut avec l'identifiant spécifié.
     * 
     * @param identifiant l'identifiant de l'attribut à vérifier
     * @return true si l'attribut est dans la relation, false sinon
     */
    public boolean hasAttribut(String identifiant) {
        for (Attribut attribut : this.getAttributs()) {
            if (attribut.identifiant().equals(identifiant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie l'attribut avec l'identiant spécifié, s'il n'existe pas, renvoie
     * null.
     * 
     * @param identifiant l'identifiant de l'attribut à recherché
     * @return l'attribut recherché ou null
     */
    public Attribut getAttribut(String identiant) {
        for (Attribut attribut : this.getAttributs()) {
            if (attribut.identifiant().equals(identiant)) {
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
    protected void verifierTuple(Tuple tuple) {
        for (Attribut attribut : this.getAttributs()) {
            if (tuple.hasIdentifiant(attribut.identifiant())) {
                Valeur valeur = tuple.getValeur(attribut.identifiant());

                if (attribut.type() != valeur.getType()) {
                    throw new TypeInvalideException(String.format(
                        "Type de valeur invalide pour l'attribut '%s', type attendu : '%s'",
                        attribut.identifiant(), attribut.type().name()));
                }
            } else {
                throw new AttributInexistantException(String.format(
                    "Attribut '%s' inexistant dans la relation '%s'",
                    attribut.identifiant(), this.identifiant));
            }
        }
    }

    /**
     * Renvoie une relation avec l'alias spécifié comme identifiant.
     * 
     * @param alias l'alias de la nouvelle relation
     * @return la relation
     */
    public Relation alias(String alias) {
        return new Relation(alias, attributs, tuples);
    }

    /**
     * Applique une sélection sur la relation à partir des attributs spécifiés.
     * 
     * @param attributs la liste attributs à sélectionner
     * @return la nouvelle relation
     */
    public Relation selectionner(Set<String> attributs) {
        Set<Attribut> attributs2 = new HashSet<>();

        for (String attribut : attributs) {
            if (!this.hasAttribut(attribut)) {
                throw new SelectionException(String.format("Attribut inexistant '%s'", attribut));
            }
            attributs2.add(this.getAttribut(attribut));
        }
        Collection<Tuple> tuples = new ArrayList<>();

        for (Tuple tuple : this.getTuples()) {
            Tuple nouveauTuple = new Tuple();

            for (String attribut : attributs) {
                Valeur valeur = tuple.getValeur(attribut);
                nouveauTuple.setValeur(attribut, valeur);
            }
            tuples.add(nouveauTuple);
        }
        return new Relation(this.identifiant, attributs2, tuples);
    }

    /**
     * Applique une projection sur la relation à partir de l'expression logique
     * spécifiée.
     * 
     * @param expression l'expression logique
     * @return la nouvelle relation
     */
    public Relation projeter(ExpressionLogique expression) {
        Collection<Tuple> tuples = new ArrayList<>();

        for (Tuple tuple : this.getTuples()){

            if (expression.evaluer(tuple)){
                tuples.add(tuple);
            }
        }

        return new Relation(this.getIdentifiant(), this.getAttributs(), tuples);
    }

    /**
     * Applique un produit cartésien sur la relation à partir d'une seconde
     * relation.
     * 
     * @param relation la relation
     * @return la nouvelle relation
     */
    public Relation produitCartesien(Relation relation) {
        if (relation.identifiant.equals(this.identifiant)) {
            throw new ProduitCartesienException("Produit cartesien impossible entre relations ayant le même identifiant");
        }
        Collection<Tuple> tuples = new ArrayList<>();
        Collection<Attribut> attributs = this.concatenerAttributs(
            this.identifiant, this.attributs,
            relation.identifiant, relation.attributs
        );

        for (Tuple tuple1 : this.getTuples()) {
            for (Tuple tuple2 : relation.getTuples()) {
                Tuple sousTuple1 = tuple1.prefixer(this.identifiant);
                Tuple sousTuple2 = tuple2.prefixer(relation.identifiant);
                tuples.add(sousTuple1.concatener(sousTuple2));
            }
        }
        String identifiant = this.concatenerIdentifiants(this.identifiant, relation.identifiant);
        return new Relation(identifiant, attributs, tuples);
    }

    public Relation produitCartesien(Collection<Relation> relations) {
        Relation produit = this;

        for (Relation relation : relations) {
            produit = produit.produitCartesien(relation);
        }
        return produit;
    }

    /**
     * Renvoie une collection d'attribut contenant la concaténation des deux
     * collections d'attributs spécifiées.
     * 
     * @param attributs1 la première liste d'attribut à concaténer
     * @param attributs1 la seconde liste d'attribut à concaténer
     * @return la collection d'attribut concaténée
     */
    private Collection<Attribut> concatenerAttributs(
        String identifiant1, Collection<Attribut> attributs1,
        String identifiant2, Collection<Attribut> attributs2
    ) {
        Collection<Attribut> attributs = new ArrayList<>();

        for (Attribut attribut : attributs1) {
            attributs.add(attribut.prefixer(identifiant1));
        }
        for (Attribut attribut : attributs2) {
            attributs.add(attribut.prefixer(identifiant2));
        }
        return attributs;
    }

    /**
     * Affiche toutes les informations de la relation.
     */
    public void afficher() {
        System.out.printf("> Table: %s\n", this.getIdentifiant());
        System.out.print("|");

        for (Attribut attribut : this.getAttributs()) {
            System.out.printf(" %-15s |", attribut.identifiant());
        }
        System.out.print("\n|");

        for (int i = 0; i < this.getAttributs().size(); i++) {
            System.out.printf("-".repeat(17));
            System.out.printf("|");
        }
        System.out.println();

        for (Tuple tuple : this.getTuples()) {
            System.out.print("|");

            for (Attribut attribut : this.getAttributs()) {
                System.out.printf(" %-15s |", tuple.getValeur(attribut.identifiant()));
            }
            System.out.println();
        }
        System.out.println();
    }

    private String concatenerIdentifiants(String identifiant1, String identifiant2) {
        return String.format("%s_%s", identifiant1, identifiant2);
    }
}
