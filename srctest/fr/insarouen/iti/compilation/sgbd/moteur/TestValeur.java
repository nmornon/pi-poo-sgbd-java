package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestValeur {

    @Test
    public void testChaine() {
        Valeur<String> batman = new Chaine("UneChaine");
        assertEquals("UneChaine", batman.getValeur());
        assertNotEquals("chaine", batman.getValeur());
    }

    @Test
    public void testNombre() {
        Valeur<Number> puissance = new Nombre(11);
        assertEquals(11, puissance.getValeur());
        assertNotEquals(12, puissance.getValeur());
    }

    @Test
    public void testCle() {
        Valeur<Number> idAuto = new Cle(12);
        assertEquals(12, idAuto.getValeur());
        assertNotEquals(11, idAuto.getValeur());
    }

    @Test
    public void testCle() {
        Valeur<Boolean> idAuto = new Booleen(true);
        assertEquals(true, idAuto.getValeur());
        assertNotEquals(false, idAuto.getValeur());
    }
}