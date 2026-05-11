package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestTuple {

    @Test
    public void testTuple() {
        Tuple tuple = new Tuple();
        Identifiant id = new Identifiant("identifiant");
        Nombre nb1 = new Nombre(12);
        Nombre nb2 = new Nombre(14);
        tuple.set(id, nb1);
        assertEquals(nb1.getValeur(), tuple.get(id).getValeur());
        assertNotEquals(nb2.getValeur(), tuple.get(id).getValeur());
    }
}