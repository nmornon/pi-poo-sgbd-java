package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestValeur {

    @Test
    public void testChaine() {
        Valeur<String> uneChaine = new Chaine("UneChaine");
        assertEquals("UneChaine", uneChaine.getValeur());
        assertNotEquals("chaine", uneChaine.getValeur());
    }

    @Test
    public void testNombre() {
        Valeur<Number> nb = new Nombre(11);
        assertEquals(11, nb.getValeur());
        assertNotEquals(12, nb.getValeur());
    }

    @Test
    public void testCle() {
        Valeur<Number> cle = new Cle(12);
        assertEquals(12, cle.getValeur());
        assertNotEquals(11, cle.getValeur());
    }

    @Test
    public void testBooleen() {
        Valeur<Boolean> bool = new Booleen(true);
        assertEquals(true, bool.getValeur());
        assertNotEquals(false, bool.getValeur());
    }
}