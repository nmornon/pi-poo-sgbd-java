package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.io.Serializable;

import fr.insarouen.iti.compilation.sgbd.exceptions.TableDejaExistanteException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;

/**
 * Classe représentant la base de données
 */
public class BaseDeDonnees implements Serializable {
    /** L'identifiant de la base de données */
    private String identifiant;
    /** Clé du prochain tuple de la base de données */
    private int prochainSerial;
    /** Booléen permettant de savoir si la base est ouverte */
    private boolean estOuvert = true;
    /** La liste des tables de la base de données */
    private Collection<Table> tables = new ArrayList<>();

    /**
     * Construit la base à partir de l'identifiant et le compteur de clés que l'on
            initialise à 1
     * 
     * @param identifiant le nom de la base
     */
    public BaseDeDonnees(String identifiant){
        this(identifiant, 1);
    }

    /**
     * Construit la base à partir de l'identifiant et le compteur de clés
     * 
     * @param identifiant nom de la base
     * @param dernierSerial dernière clé utilisée
     */
    public BaseDeDonnees(String identifiant, int dernierSerial){
        this(identifiant, new ArrayList<>(), dernierSerial);
    }

    /**
     * Construit la base à partir de l'identifiant, des tables fournies et
            le compteur de clés que l'on initialise à 1
     * 
     * @param identifiant nom de la base
     * @param tables tables à charger dans la base
     */
    public BaseDeDonnees(String identifiant, Collection<Table> tables){
        this(identifiant, tables, 1);
    }

    /**
     * Construit la base à partir de l'identifiant, le compteur de clés et
            des tables fournies
     *
     * @param identifiant nom de la base
     * @param dernierSerial dernière clé utilisée
     * @param tables tables à charger dans la base
     */
    public BaseDeDonnees(String identifiant, Collection<Table> tables, int dernierSerial){
        this.identifiant = identifiant;
        this.tables.addAll(tables);
        this.prochainSerial = dernierSerial;
    }

    /**
     * Indique si la base est ouverte
     * 
     * @return true si la base est ouverte, false sinon
     */
    public boolean getEstOuverte() {
        return this.estOuvert;
    }

    /**
     * Ouvre ou ferme la base
     * 
     * @param bool true pour ouvrir la base, false pour la fermer
     */
    public void setEstOuverte(boolean bool) {
        this.estOuvert = bool;
    }

    /**
     * Renvoie l'identifiant de la base
     * 
     * @return le nom de la base
     */
    public String getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Génère la prochaine clé
     * 
     * @return la prochaine clé de type Cle
     */
    public Cle getProchaineCle() {
        return new Cle(this.prochainSerial++);
    }

    /**
     * Renvoie une copie de la collection des tables de la base
     * 
     * @return les tables de la base
     */
    public Collection<Table> getTables() {
        return new ArrayList <> (this.tables);
    }

    /**
     * Vérifie qu'une table avec l'identifiant spécifié existe
     *
     * @param identifiant nom de la table que l'on cherche
     * @return true si la table existe, false sinon
     */
    public boolean hasTable(String identifiant) {
        for (Table table : this.tables) {
            if (table.getIdentifiant().equals(identifiant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie une table à partir de son identifiant
     *
     * @param identifiant nom de la table que l'on cherche
     * @return table correspondante
     * @throws TableInexistanteException si aucune table ne possède ce nom
     */
    public Table getTable(String identifiant) {
        for (Table table : this.tables) {
            if (table.getIdentifiant().equals(identifiant)) {
                return table;
            }
        }
        throw new TableInexistanteException(String.format(
            "La table '%s' n'est pas présente dans la base de données",
            identifiant));
    }

    /**
     * Crée une table vide avant de l'ajouter à la base
     *
     * @param identifiant nom de la table à ajouter
     * @param donnees colonnes de la table
     * @return la table créée
     * @throws TableDejaExistanteException si une table de même identifiant existe déjà
     */
    public Table creerTable(String identifiant, Collection<Colonne> donnees) {
        if (this.hasTable(identifiant)) {
            throw new TableDejaExistanteException(String.format("la table '%s' existe déjà", identifiant));
        }
        Table table = new Table(this, identifiant, donnees);
        this.tables.add(table);
        return table;
    }

    /**
     * Crée une table remplie avant de l'ajouter à la base
     *
     * @param identifiant nom de la table à ajouter
     * @param donnees colonnes de la table
     * @param tuples typles de la table
     * @return la table créée
     */
    public Table creerTable(String identifiant, Collection<Colonne> donnees, Collection<Tuple> tuples) {
        Table table = new Table(this, identifiant, donnees, tuples);
        this.tables.add(table);
        return table;
    }

    /**
     * Supprime une table de la base
     *
     * @param identifiant nom de la table à supprimer
     * @throws TableInexistanteException si la table n'existe pas
     */
    public void supprimerTable(String identifiant)  throws TableInexistanteException{
        Iterator<Table> it = this.tables.iterator();

        while (it.hasNext()) {
            Table o = it.next();

            if (o.getIdentifiant().equals(identifiant)) {
                it.remove();
                return;
            }
        }
        throw new TableInexistanteException(String.format(
            "La table '%s' n'est pas présente dans la base de données",
            identifiant));
    }

    /**
     * Affiche toutes les tables de la base
     */
    public String toString() {
        StringBuilder texte = new StringBuilder();
        for (Table o : this.tables) {
            texte.append(String.format("Identifiant de la base : %s \n", this.identifiant));
            texte.append(String.format("Table %s \n", o.getIdentifiant()));
            texte.append(String.format("\n", o.toString()));
            texte.append(String.format("--------------------- \n"));
        }
        return texte.toString();
    }

    /** Compare deux bases à l'aide de leur identifiant
     * 
     * @param object objet à comparer avec la base
     * @return true si les identifiants sont les mêmes, false sinon
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof BaseDeDonnees baseDeDonnees)){
            return false;
        } 
        return this.identifiant.equals(baseDeDonnees.identifiant);
    }
}
