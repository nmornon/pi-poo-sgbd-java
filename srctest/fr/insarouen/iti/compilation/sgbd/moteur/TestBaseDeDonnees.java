package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;
import fr.insarouen.iti.compilation.sgbd.moteur.Attribut;
import fr.insarouen.iti.compilation.sgbd.moteur.Table;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;


public class testBaseDeDonnees {

    private BaseDeDonnees bdd1;
    private BaseDeDonnees bdd2;
    private BaseDeDonnees bdd3;

    private Identifiant id1;
    private Identifiant id2;
    private Identifiant id_table;

    private Collection<Attribut> attributs;
    private Collection<Tuple> tuples;

    private Table table;

    @Before
    public void avantTest() {
        id1 = new Identifiant("bdd1");
        id2 = new Identifiant("bdd2");
        id_table = new Identifiant("Table1");
        Identifiant id_attribut = new Identifiant("Age");
        Identifiant id_tuple = new Identifiant("Michel");
        

        attributs = new ArrayList<>();
        att1 = new Attribut(id_attribut, Type.INT);
        attributs.add(att1);

        tuples = new ArrayList<>();
        tuple = new Tuple();
        tuple.set(id_tuple, 8);
        tuples.add(tuple);

        table = new Table(id_table, attributs);

        Collection<Table> tables = new ArrayList<>();
        
        long ligne = 0;

        bdd1 = new BaseDeDonnees(id1, tables, ligne);
        bdd2 = new BaseDeDonnees(id1, tables, ligne);
        bdd3 = new BaseDeDonnees(id2, tables, ligne);
    }

    @Test
    public void testBaseDeDonnees() {
        assertTrue(bdd1.equals(bdd2));
        assertFalse(bdd1.equals(bdd3));
    }

    @Test
    public void testTable() throws TableInexistanteException{
        bdd1.creerTable(id_table, attributs);
        assertThat(bdd1.getTable(id_table), equalTo(table));
    }

    @Test(expected = TableInexistanteException.class)
    public void testTableInexistante() throws TableInexistanteException{
        bdd1.getTable(id_table);
    }

    @Test(expected = TableInexistanteException.class)
    public void testSupprimerTableException() throws TableInexistanteException{
        bdd1.supprimerTable(id_table);
    }
    
    @Test(expected = TableInexistanteException.class)
    public void testSupprimerTable() throws TableInexistanteException{
        Table table1 = new Table(id_table, attributs, tuples);
        
        bdd1.creerTable(id_table, attributs, tuple);
        bdd1.supprimerTable(id_table);
        bdd1.getTable(id_table);
    }
    

}