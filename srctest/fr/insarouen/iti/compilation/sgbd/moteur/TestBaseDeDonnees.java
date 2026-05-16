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

public class TestBaseDeDonnees {
    private BaseDeDonnees bdd;
    private Collection<Attribut> attributs;

    @Before
    public void avantTest() {
        bdd = new BaseDeDonnees("base");
        attributs = List.of(
            new Attribut("id", Type.SERIAL),
            new Attribut("nom", Type.VARCHAR),
            new Attribut("age", Type.INT)
        );
    }

    @Test
    public void test_creerTable() throws TableInexistanteException{
        bdd.creerTable("table", attributs);
        assertThat(bdd.hasTable("table"), is(true));
    }

    @Test(expected = TableDejaExistanteException.class)
    public void test_creerTable_avecTableDejaExistante() throws TableInexistanteException{
        bdd.creerTable("table", attributs);
        bdd.creerTable("table", attributs);
    }

    @Test(expected = TableInexistanteException.class)
    public void test_getTable_avecTableInexistante() throws TableInexistanteException{
        bdd.getTable("table_inexistante");
    }
    
    @Test
    public void test_supprimerTable() throws TableInexistanteException{
        bdd.creerTable("table", attributs);
        bdd.supprimerTable("table");
        assertThat(bdd.hasTable("table"), is(false));
    }

    @Test(expected = TableInexistanteException.class)
    public void test_supprimerTable_avecTableInexistante() throws TableInexistanteException{
        bdd.supprimerTable("table_inexistante");
    }

    @Test
    public void test_equals() {
        assertThat(bdd, equalTo(new BaseDeDonnees("base")));
    }
}