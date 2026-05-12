package fr.insarouen.iti.compilation.sgbd.moteur;


import java.util.ArrayList;
import java.util.Collection;

import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;
import fr.insarouen.iti.compilation.sgbd.moteur.Table;
import fr.insarouen.iti.compilation.sgbd.moteur.Attribut;
import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;

public class BaseDeDonnees {
    private Identifiant identifiant;
    private long nbLignes;
    private Collection<Table> tables = new ArrayList<>();
    

    public BaseDeDonnees(Identifiant id, Collection<Table> tabs, long ligne){
        this.identifiant = id;
        this.tables.addAll(tabs);
        this.nbLignes = ligne;
        
    }

    public Identifiant getId() {
        return this.identifiant;
    }

    public long getNbLignes() {
        return this.nbLignes;
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

    public Table getTable(Identifiant id) throws TableInexistanteException{
        for (Table o : this.tables) {
            if (o.getIdentifiant().equals(id)) {
                return o;
            }
        }
        throw new TableInexistanteException("La table n'est pas présente dans la base de donnees");
    }

    public Table creerTable(Identifiant id, Collection<Attribut> donnees) {
        Table table = new Table(id, donnees);
        this.tables.add(table);
        return table;
    }

    public Table creerTable(Identifiant id, Collection<Attribut> donnees, Collection<Tuple> tuples) {
        Table table = new Table(id, donnees, tuples);
        this.tables.add(table);
        return table;
    }

    public void supprimerTable(Identifiant id)  throws TableInexistanteException{
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

    public Boolean equals(Identifiant id) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseDeDonnees)){
            return false;
        } 
        BaseDeDonnees autre = (BaseDeDonnees) obj;
        return this.identifiant.equals(autre.identifiant);
    }

}
