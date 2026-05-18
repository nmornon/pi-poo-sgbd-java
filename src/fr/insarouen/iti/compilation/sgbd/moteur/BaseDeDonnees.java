package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.io.Serializable;

import fr.insarouen.iti.compilation.sgbd.exceptions.TableDejaExistanteException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;

public class BaseDeDonnees implements Serializable {
    private String identifiant;
    private int prochainSerial;
    private boolean estOuvert = true;
    private Collection<Table> tables = new ArrayList<>();

    /**
     * Constructeur de la classe BaseDeDonnees
     * @param identifiant identifiant de la BaseDeDonnees
     * 
     */
    public BaseDeDonnees(String identifiant){
        this(identifiant, 1);
    }

    /**
     * Constructeur de la classe BaseDeDonnees
     * @param identifiant identifiant de la BaseDeDonnees
     * @param dernierSerial dernière ligne correspondant au dernier tuple de la base
     * 
     */
    public BaseDeDonnees(String identifiant, int dernierSerial){
        this(identifiant, new ArrayList<>(), dernierSerial);
    }

    /**
     * Constructeur de la classe BaseDeDonnees
     * @param identifiant identifiant de la BaseDeDonnees
     * @param tables liste de table 
     * 
     */
    public BaseDeDonnees(String identifiant, Collection<Table> tables){
        this(identifiant, tables, 1);
    }

    /**
     * Constructeur de la classe BaseDeDonnees
     * @param identifiant identifiant de la BaseDeDonnees
     * @param tables liste de table 
     * @param dernierSerial dernière ligne correspondant au dernier tuple de la base
     * 
     */
    public BaseDeDonnees(String identifiant, Collection<Table> tables, int dernierSerial){
        this.identifiant = identifiant;
        this.tables.addAll(tables);
        this.prochainSerial = dernierSerial;
    }

    public boolean getEstOuverte() {
        return this.estOuvert;
    }

    public void setEstOuverte(boolean bool) {
        this.estOuvert = bool;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public Cle getProchaineCle() {
        return new Cle(this.prochainSerial++);
    }

    public Collection<Table> getTables() {
        return new ArrayList <> (this.tables);
    }

    /**
     * Vérifie qu'une table est dans la base de données
     * @param identifiant identifiant de la table
     * 
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
     * Récupère une table de la base de données
     * @param identifiant identifiant de la table
     * 
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
     * Crée une nouvelle table dans la base de données
     * @param identifiant identifiant de la table
     * @param donnees les données contenue dans la table
     * 
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
     * Crée une nouvelle table dans la base de données
     * @param identifiant identifiant de la table
     * @param donnees les attributs de la table
     * @param tuples les données contenue dans la table pour chaque attributs
     * 
     */
    public Table creerTable(String identifiant, Collection<Colonne> donnees, Collection<Tuple> tuples) {
        Table table = new Table(this, identifiant, donnees, tuples);
        this.tables.add(table);
        return table;
    }

    /**
     * Supprime une table de la base de données
     * @param identifiant identifiant de la table
     * 
     * @throws TableInexistanteException
     * 
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
     * Affiche l'ensemble des tables contenue dans la base de données
     * 
     */
    public void afficherTables() {
        for (Table o : this.tables) {
            o.afficher();
            System.out.println(" ------------------------------- ");
        }
    }

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
