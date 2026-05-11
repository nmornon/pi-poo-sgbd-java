package fr.insarouen.iti.compilation.sgbd.moteur;


import java.util.Arraylist;
import java.util.Collection;

import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;
import fr.insarouen.iti.compilation.sgbd.moteur.Table;
import fr.insarouen.iti.compilation.sgbd.moteur.Colonne;
import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;

public class BaseDeDonnees {
    private Identifiant identifiant;
    private long nbLignes;
    private Collection<Table> tables = new Arraylist();
    

    public BaseDeDonnees(Identifiant id, Collection<Table> tabs, long ligne) {
        this.identifiant = id;
        boolean b = true;
        b = this.tables.addAll(tabs);
        if (!b) {
            throw new Error("Impossible de creer une nouvelle avec ces tables");
        }
        this.nbLignes = ligne;
        
    }

    public Identifiant getId() {
        return this.identifiant;
    }

    public long getCle() {
        return this.nbCle;
    }

    public Collection<Table> getTables() {
        return new ArrayList <> (this.tables);
    }

    public void setId(Identifiant id) {
        this.identifiant = id;
    }

    public void setLigne(long ligne) {
        this.nbLignes = ligne;
    } 

    public Table getTable(Identifiant id) {
        for (Table o : this.tables) {
            if (o.getIdentifiant.equals(id)) {
                return o;
            }
        }
        throw new TableInexistanteException("La table n'est pas présente dans la base de donnees");
    }

    public Table creerTable(Identifiant id, Collection<Colonne> donnees) {
        Table table = new Table(id, donnees);
        boolean b = true;
        b = this.tables.add(table);
        if (!b) {
            throw new Error("Impossible d'ajouter la table");
        }
        return table;
    }

    public void supprimerTable(Identifiant id) {
        for (Table o : this.tables) {
            if (o.getIdentifiant.equals(id)) {
                boolean b = true;
                b = this.tables.remove(o);
                if (!b) {
                    throw new Error("Ne peut pas être supprimer");
                }
                break;
            }
        }
        throw new TableInexistanteException("La table n'est pas présente dans la base de donnees");
    }

    public void afficherTables() {
        for (Table o : this.tables) {
            o.afficher();
            System.out.println(" ------------------------------- ");
        }
    }

    public Boolean equals(Identifiant id) {
        return this.identifiant == id;
    }

}
