package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestIdentifiant {

    @Test
    public void testIdentifiant() {
        Identifiant id1 = new Identifiant("identifiant");
        Identifiant id2 = new Identifiant("identifiant");
        Identifiant id3 = new Identifiant("id");
        assertTrue(id1.equals(id2));
        assertFalse(id1.equals(id3));
    }
}