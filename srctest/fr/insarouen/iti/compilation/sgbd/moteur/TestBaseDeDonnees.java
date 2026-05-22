package fr.insarouen.iti.compilation.sgbd.moteur;

import java.util.Collection;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.exceptions.TableDejaExistanteException;
import fr.insarouen.iti.compilation.sgbd.exceptions.TableInexistanteException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;

public class TestBaseDeDonnees {
    private BaseDeDonnees bdd;
    private Collection<Colonne> colonnes;

    @Before
    public void avantTest() {
        bdd = new BaseDeDonnees("base");
        colonnes = List.of(
            new Colonne("id", Type.SERIAL),
            new Colonne("nom", Type.VARCHAR),
            new Colonne("age", Type.INT)
        );
    }

    @Test
    public void test_creerTable() throws TableInexistanteException{
        bdd.creerTable("table", colonnes);
        assertThat(bdd.hasTable("table"), is(true));
    }

    @Test(expected = TableDejaExistanteException.class)
    public void test_creerTable_avecTableDejaExistante() throws TableInexistanteException{
        bdd.creerTable("table", colonnes);
        bdd.creerTable("table", colonnes);
    }

    @Test(expected = TableInexistanteException.class)
    public void test_getTable_avecTableInexistante() throws TableInexistanteException{
        bdd.getTable("table_inexistante");
    }
    
    @Test
    public void test_supprimerTable() throws TableInexistanteException{
        bdd.creerTable("table", colonnes);
        bdd.supprimerTable("table");
        assertThat(bdd.hasTable("table"), is(false));
    }

    @Test(expected = TableInexistanteException.class)
    public void test_supprimerTable_avecTableInexistante() throws TableInexistanteException{
        bdd.supprimerTable("table_inexistante");
    }

    @Test
    public void test_getIdentifiant() {
        assertThat(bdd.getIdentifiant(), is("base"));
    }

    @Test
    public void test_estOuverte() {

        bdd.setEstOuverte(false);

        assertThat(bdd.getEstOuverte(), is(false));
    }

    @Test
    public void test_getProchaineCle() {

        assertThat(bdd.getProchaineCle(), is(new Cle(1)));
        assertThat(bdd.getProchaineCle(), is(new Cle(2)));
    }

    @Test
    public void test_getTables() {

        bdd.creerTable("table", colonnes);

        assertThat(bdd.getTables().size(), is(1));
    }

    @Test
    public void test_getTable() {

        bdd.creerTable("table", colonnes);

        assertThat(
            bdd.getTable("table").getIdentifiant(),
            is("table")
        );
    }

    @Test
    public void test_equals() {
        BaseDeDonnees bdd1 = new BaseDeDonnees("base");
        BaseDeDonnees bdd2 = new BaseDeDonnees("base");
        Table table1 = bdd1.creerTable("table", colonnes);
        Table table2 = bdd2.creerTable("table", colonnes);

        table1.inserer(new Tuple().setValeur("age",new Entier(20))
                                    .setValeur("nom",new Chaine("nomTest")));
        table2.inserer(new Tuple().setValeur("age",new Entier(20))
                                    .setValeur("nom",new Chaine("nomTest")));
        assertThat(bdd1, equalTo(bdd2));
        assertThat(bdd1.hashCode(), is(bdd2.hashCode()));
    }



}