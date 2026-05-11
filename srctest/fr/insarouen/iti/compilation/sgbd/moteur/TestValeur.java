package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestValeur {

    @Test
    public void testChaine() {
        Valeur uneChaine = new Chaine("UneChaine");
        assertEquals("UneChaine", uneChaine.getValeur());
        assertEquals(Type.VARCHAR, uneChaine.getType());
        assertNotEquals("chaine", uneChaine.getValeur());
    }

    @Test
    public void testNombre() {
        Valeur nb = new Nombre(11);
        assertEquals(11, nb.getValeur());
        assertEquals(Type.INT, nb.getType());
        assertNotEquals(12, nb.getValeur());
    }

    @Test
    public void testCle() {
        Valeur cle = new Cle(12);
        assertEquals(12, cle.getValeur());
        assertEquals(Type.SERIAL, cle.getType());
        assertNotEquals(11, cle.getValeur());
    }
}