package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
}