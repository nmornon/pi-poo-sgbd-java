package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class TestTuple {

    @Test
    public void testTuple() {
        Tuple tuple = new Tuple();
        String id = "identifiant";
        Entier nb1 = new Entier(12);
        Entier nb2 = new Entier(14);
        tuple.setValeur(id, nb1);
        assertEquals(nb1.getValeur(), tuple.getValeur(id).getValeur());
        assertNotEquals(nb2.getValeur(), tuple.getValeur(id).getValeur());
    }

    @Test
    public void test_hasIdentifiant() {

        Tuple tuple = new Tuple();

        tuple.setValeur("age", new Entier(40));

        assertThat(tuple.hasIdentifiant("age"), is(true));
        assertThat(tuple.hasIdentifiant("nom"), is(false));
    }

    @Test
    public void test_getValeurs() {

        Tuple tuple = new Tuple();

        tuple.setValeur("age", new Entier(40));

        assertThat(tuple.getValeurs().size(), is(1));
    }

    @Test
    public void test_getValeur_inexistante() {

        Tuple tuple = new Tuple();

        assertThat(tuple.getValeur("inexistant"), nullValue());
    }

    @Test
    public void test_prefixer() {

        Tuple tuple = new Tuple();

        tuple.setValeur("nom", new Chaine("Batman"));

        Tuple resultat = tuple.prefixer("heroes");

        assertThat(resultat.hasIdentifiant("heroes.nom"), is(true));
        assertThat(resultat.hasIdentifiant("nom"), is(false));
    }

    @Test
    public void test_prefixer_colonneDejaPrefixee() {

        Tuple tuple = new Tuple();

        tuple.setValeur("heroes.nom", new Chaine("Batman"));

        Tuple resultat = tuple.prefixer("bases");

        assertThat(resultat.hasIdentifiant("heroes.nom"), is(true));
        assertThat(resultat.hasIdentifiant("bases.heroes.nom"), is(false));
    }

    @Test
    public void test_concatener() {

        Tuple t1 = new Tuple();
        t1.setValeur("nom", new Chaine("Batman"));

        Tuple t2 = new Tuple();
        t2.setValeur("age", new Entier(40));

        Tuple resultat = t1.concatener(t2);

        assertThat(resultat.hasIdentifiant("nom"), is(true));
        assertThat(resultat.hasIdentifiant("age"), is(true));
    }

    @Test
    public void test_toString() {

        Tuple tuple = new Tuple();

        tuple.setValeur("age", new Entier(40));

        assertThat(tuple.toString().contains("40"), is(true));
    }

}