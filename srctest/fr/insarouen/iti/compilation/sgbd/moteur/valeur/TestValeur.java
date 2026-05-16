package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.moteur.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestValeur {

    @Test
    public void testChaine() {
        Valeur uneChaine = new Chaine("UneChaine");
        Valeur uneAutreChaine = new Chaine("UneAutreChaine");
        Valeur encoreUneAutreChaine = new Chaine("UneChaine");
        assertEquals("UneChaine", uneChaine.getValeur());
        assertNotEquals("AutreChose", uneChaine.getValeur());
        assertEquals(Type.VARCHAR, uneChaine.getType());
        assertNotEquals(Type.INT, uneChaine.getType());
        assertFalse(uneChaine.equals(uneAutreChaine));
        assertTrue(uneChaine.equals(encoreUneAutreChaine));
    }

    @Test
    public void testNombre() {
        Valeur nb = new Entier(11);
        Valeur memeNb = new Entier(11);
        Valeur autreNb = new Entier(12);
        assertEquals(11, nb.getValeur());
        assertNotEquals(12, nb.getValeur());
        assertEquals(Type.INT, nb.getType());
        assertNotEquals(Type.SERIAL, nb.getType());
        assertTrue(nb.equals(memeNb));
        assertFalse(nb.equals(autreNb));
    }

    @Test
    public void testCle() {
        Valeur cle = new Cle(12);
        Valeur memeCle = new Cle(12);
        Valeur autreCle = new Cle(11);
        assertEquals(12, cle.getValeur());
        assertNotEquals(11, cle.getValeur());
        assertEquals(Type.SERIAL, cle.getType());
        assertNotEquals(Type.VARCHAR, cle.getType());
        assertTrue(cle.equals(memeCle));
        assertFalse(cle.equals(autreCle));
    }
}