package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;

public class BaseDeDonnees {
    private String identifiant;
    private long nbLignes;
    private Collection<Table> tables = new ArrayList<>();
    

    public BaseDeDonnees(String identifiant, Collection<Table> tabs, long ligne){
        this.identifiant = identifiant;
        this.tables.addAll(tabs);
        this.nbLignes = ligne;
        
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public long getNbLignes() {
        return this.nbLignes;
    }

    public Collection<Table> getTables() {
        return new ArrayList <> (this.tables);
    }

    public void setLigne(long ligne) {
        this.nbLignes = ligne;
    } 

    public Table getTable(String identifiant) throws TableInexistanteException{
        for (Table o : this.tables) {
            if (o.getIdentifiant().equals(identifiant)) {
                return o;
            }
        }
        throw new TableInexistanteException("La table n'est pas présente dans la base de donnees");
    }

    public Table creerTable(String identifiant, Collection<Attribut> donnees) {
        Table table = new Table(identifiant, donnees);
        this.tables.add(table);
        return table;
    }

    public Table creerTable(String identifiant, Collection<Attribut> donnees, Collection<Tuple> tuples) {
        Table table = new Table(identifiant, donnees, tuples);
        this.tables.add(table);
        return table;
    }

    public void supprimerTable(String id)  throws TableInexistanteException{
        Iterator<Table> it = this.tables.iterator();
        while (it.hasNext()) {
            Table o = it.next();

            if (o.getIdentifiant().equals(id)) {
                it.remove();
                return;
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
