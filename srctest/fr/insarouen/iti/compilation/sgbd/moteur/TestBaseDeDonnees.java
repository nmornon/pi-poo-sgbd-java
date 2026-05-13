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
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Nombre;


import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;


public class TestBaseDeDonnees {

    private BaseDeDonnees bdd1;
    private BaseDeDonnees bdd2;
    private BaseDeDonnees bdd3;

    private Collection<Attribut> attributs;
    private Collection<Tuple> tuples;

    private Table table;

    @Before
    public void avantTest() {      

        attributs = new ArrayList<>();
        Attribut att1 = new Attribut("Age", Type.INT);
        attributs.add(att1);

        tuples = new ArrayList<>();
        Valeur val1 = new Nombre(8);
        Tuple tuple = new Tuple();
        tuple.setValeur("Didier", val1);
        tuples.add(tuple);

        table = new Table("tb1", attributs);

        Collection<Table> tables = new ArrayList<>();
        
        long ligne = 0;

        bdd1 = new BaseDeDonnees("base1", tables, ligne);
        bdd2 = new BaseDeDonnees("base1", tables, ligne);
        bdd3 = new BaseDeDonnees("base3", tables, ligne);
    }

    @Test
    public void testBaseDeDonnees() {
        assertTrue(bdd1.equals(bdd2));
        assertFalse(bdd1.equals(bdd3));
    }

    @Test
    public void testTable() throws TableInexistanteException{
        bdd1.creerTable("tb1", attributs);
        assertThat(bdd1.getTable("tb1"), equalTo(table));
    }

    @Test(expected = TableInexistanteException.class)
    public void testTableInexistante() throws TableInexistanteException{
        bdd1.getTable("tb1");
    }

    @Test(expected = TableInexistanteException.class)
    public void testSupprimerTableException() throws TableInexistanteException{
        bdd1.supprimerTable("tb1");
    }
    
    @Test(expected = TableInexistanteException.class)
    public void testSupprimerTable() throws TableInexistanteException{
        Table table1 = new Table("tb1", attributs, tuples);
        
        bdd1.creerTable("tb1", attributs, tuples);
        bdd1.supprimerTable("tb1");
        bdd1.getTable("tb1");
    }
    

}