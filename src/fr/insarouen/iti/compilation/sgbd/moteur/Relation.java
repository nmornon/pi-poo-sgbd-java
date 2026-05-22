package fr.insarouen.iti.compilation.sgbd.moteur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneDejaExistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneInexistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.ProduitCartesienException;
import fr.insarouen.iti.compilation.sgbd.exceptions.RelationSansColonneException;
import fr.insarouen.iti.compilation.sgbd.exceptions.SelectionException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

/**
 * Classe représentant une relation.
 */
public class Relation implements Serializable {
    /** L'identifiant de la relation */
    private String identifiant;
    /** La liste des colonnes de la relation */
    private Set<Colonne> colonnes = new HashSet<>();
    /** La liste des tuples de la relation */
    private Collection<Tuple> tuples = new ArrayList<>();

    /**
     * Construit une relation à partir de la liste d'colonnes spécifiée.
     * 
     * @param colonnes la liste des colonnes
     */
    public Relation(String identifiant, Collection<Colonne> colonnes) {
        this(identifiant, colonnes, new ArrayList<>());
    }

    /**
     * Construit une relation à partir de la liste d'colonnes spécifiée et de
     * la liste de tuples spécifiée.
     * 
     * @param identifiant l'identifiant de la relation
     * @param colonnes la liste des colonnes
     * @param tuples    la liste des tuple
     * 
     * @throws TypeInvalideException si au moins un tuple ne correspond pas aux
     *                       colonnes
     */
    public Relation(String identifiant, Collection<Colonne> colonnes, Collection<Tuple> tuples) {
        this.identifiant = identifiant;

        if (colonnes.isEmpty()) {
            throw new RelationSansColonneException("Une relation doit avoir au moins un colonne");
        }

        for (Colonne colonne : colonnes) {
            if (this.hasColonne(colonne.identifiant())) {
                throw new ColonneDejaExistantException("Deux colonnes ne peuvent pas avoir le même identifiant");
            }
            this.colonnes.add(colonne);
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
     * Détermine si la relation a un colonne avec l'identifiant spécifié.
     * 
     * @param identifiant l'identifiant de la colonne à vérifier
     * @return true si la colonne est dans la relation, false sinon
     */
    public boolean hasColonne(String identifiant) {
        for (Colonne colonne : this.getColonnes()) {
            if (colonne.identifiant().equals(identifiant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie la colonne avec l'identiant spécifié, s'il n'existe pas, renvoie
     * null.
     * 
     * @param identifiant l'identifiant de la colonne à recherché
     * @return la colonne recherché ou null
     */
    public Colonne getColonne(String identifiant) {
        for (Colonne colonne : this.getColonnes()) {
            if (colonne.identifiant().equals(identifiant)) {
                return colonne;
            }
        }
        return null;
    }

    /**
     * Renvoie une collection des colonnes de la relation
     */
    public Collection<Colonne> getColonnes() {
        return this.colonnes;
    }

    /**
     * Renvoie une collection des tuples de la relation
     */
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
        for (Colonne colonne : this.getColonnes()) {
            if (tuple.hasIdentifiant(colonne.identifiant())) {
                Valeur valeur = tuple.getValeur(colonne.identifiant());

                if (colonne.type() != valeur.getType()) {
                    throw new TypeInvalideException(String.format(
                        "Type de valeur invalide pour la colonne '%s', type attendu : '%s'",
                        colonne.identifiant(), colonne.type().name()));
                }
            } else {
                throw new ColonneInexistantException(String.format(
                    "Colonne '%s' inexistant dans la relation '%s'",
                    colonne.identifiant(), this.identifiant));
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
        Collection<Colonne> colonnes = new ArrayList<>();
        Collection<Tuple> tuples = new ArrayList<>();

        for (Colonne colonne : this.colonnes) {
            colonnes.add(colonne.prefixer(alias));
        }
        for (Tuple tuple : this.tuples) {
            tuples.add(tuple.prefixer(alias));
        }
        return new Relation(alias, colonnes, tuples);
    }

    /**
     * Applique une sélection sur la relation à partir des colonnes spécifiés.
     * 
     * @param colonnes la liste colonnes à sélectionner
     * @return la nouvelle relation
     */
    public Relation selectionner(Set<String> colonnes) {
        Set<Colonne> colonnes2 = new HashSet<>();

        for (String colonne : colonnes) {
            if (!this.hasColonne(colonne)) {
                throw new SelectionException(String.format("Colonne inexistant '%s'", colonne));
            }
            colonnes2.add(this.getColonne(colonne));
        }
        Collection<Tuple> tuples = new ArrayList<>();

        for (Tuple tuple : this.getTuples()) {
            Tuple nouveauTuple = new Tuple();

            for (String colonne : colonnes) {
                Valeur valeur = tuple.getValeur(colonne);
                nouveauTuple.setValeur(colonne, valeur);
            }
            tuples.add(nouveauTuple);
        }
        String identifiant = this.concatenerIdentifiants(this.identifiant, "selection");
        return new Relation(identifiant, colonnes2, tuples);
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
        String identifiant = this.concatenerIdentifiants(this.identifiant, "projection");
        return new Relation(identifiant, this.colonnes, tuples);
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
        Collection<Colonne> colonnes = this.concatenerColonnes(
            this.identifiant, this.colonnes,
            relation.identifiant, relation.colonnes
        );

        for (Tuple tuple1 : this.getTuples()) {
            for (Tuple tuple2 : relation.getTuples()) {
                Tuple sousTuple1 = tuple1.prefixer(this.identifiant);
                Tuple sousTuple2 = tuple2.prefixer(relation.identifiant);
                tuples.add(sousTuple1.concatener(sousTuple2));
            }
        }
        String identifiant = this.concatenerIdentifiants(this.identifiant, relation.identifiant);
        return new Relation(identifiant, colonnes, tuples);
    }
    
    // public Relation produitCartesien(Collection<Relation> relations) {
    //     Relation produit = this;

    //     for (Relation relation : relations) {
    //         produit = produit.produitCartesien(relation);
    //     }
    //     return produit;
    // }

    /**
     * Renvoie une collection d'colonne contenant la concaténation des deux
     * collections d'colonnes spécifiées.
     * 
     * @param colonnes1 la première liste d'colonne à concaténer
     * @param colonnes1 la seconde liste d'colonne à concaténer
     * @return la collection d'colonne concaténée
     */
    private Collection<Colonne> concatenerColonnes(
        String identifiant1, Collection<Colonne> colonnes1,
        String identifiant2, Collection<Colonne> colonnes2
    ) {
        Collection<Colonne> colonnes = new ArrayList<>();

        for (Colonne colonne : colonnes1) {
            colonnes.add(colonne.prefixer(identifiant1));
        }
        for (Colonne colonne : colonnes2) {
            colonnes.add(colonne.prefixer(identifiant2));
        }
        return colonnes;
    }

    /**
     * Affiche toutes les informations de la relation.
     */
    @Override
    public String toString() {
        Map<String,Integer> laMap = new HashMap<>();
        for (Colonne colonne : this.getColonnes()) {
            laMap.put(colonne.identifiant(),colonne.identifiant().length());
        }
        for (Tuple tuple : this.getTuples()) {

            for (Colonne colonne : this.getColonnes()) {
                int longueurVal = tuple.getValeur(colonne.identifiant()).toString().length();

                if (longueurVal > laMap.get(colonne.identifiant())) {
                    laMap.put(colonne.identifiant(),longueurVal);
                }
            }
        }
        System.out.println(laMap);


        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append(": ");
        builder.append(this.getIdentifiant());
        builder.append("\n|");

        for (Colonne colonne : this.getColonnes()) {
            builder.append(String.format(String.format(" %%-%ds |",laMap.get(colonne.identifiant())),colonne.identifiant()));
        }
        builder.append("\n|");

        for (Colonne colonne : this.getColonnes()) {
            builder.append(String.format(" %s ", "-".repeat(laMap.get(colonne.identifiant()))));
            builder.append(String.format("|"));
        }
        builder.append("\n");

        for (Tuple tuple : this.getTuples()) {
            builder.append("|");

            for (Colonne colonne : this.getColonnes()) {
                builder.append(String.format(String.format(" %%-%ds |",laMap.get(colonne.identifiant())),tuple.getValeur(colonne.identifiant())));
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    private String concatenerIdentifiants(String identifiant1, String identifiant2) {
        return String.format("%s_%s", identifiant1, identifiant2);
    }

    /** Compare deux relations à l'aide de leur identfiant, colonnes et tuples
     * 
     * @param object objet à comparer avec la relation
     * @return true si ces conditions sont respectees
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Relation relation)) {
            return false;
        }
        return this.identifiant.equals(relation.identifiant)
            && this.colonnes.equals(relation.colonnes)
            && this.tuples.equals(relation.tuples);
    }

    /**
     * Renvoie le hashcode de la colonne
     * @return le code de hashage de la colonne
     */
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + this.identifiant.hashCode();
        hash = hash * 17 + this.colonnes.hashCode();
        hash = hash * 19 + this.tuples.hashCode();
        return hash;
    }
}
