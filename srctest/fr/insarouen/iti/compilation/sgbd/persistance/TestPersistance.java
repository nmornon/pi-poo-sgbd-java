package fr.insarouen.iti.compilation.sgbd.persistance;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;


public class TestPersistance {
    private BaseDeDonnees bdd;
    private GestionnairePersistance gestionnaire;

    @Before
    public void avantTest() {
        bdd = new BaseDeDonnees("base");
        gestionnaire = new GestionnairePersistance();
    }

    @Test
    public void test_chargerFichierInexistant() throws IOException{
        assertThat(gestionnaire.charger("Base"), equalTo(new BaseDeDonnees("Base")));
    }

    @Test
    public void test_sauvegarde_et_chargement() throws IOException{
        bdd.setEstOuverte(false);
        gestionnaire.sauvegarder(bdd);

        BaseDeDonnees test = gestionnaire.charger("base");

        assertThat("base", equalTo(test.getIdentifiant()));
    }

    @Test
    public void test_chargement_estOuverte() throws IOException{
        bdd.setEstOuverte(false);
        gestionnaire.sauvegarder(bdd);

        BaseDeDonnees test = gestionnaire.charger("base");

        assertThat(test.getEstOuverte(), equalTo(true));
    }

    @Test
    public void test_creation_fichier() throws IOException{
        gestionnaire.sauvegarder(bdd);

        File fichier = new File("base");

        assertThat(fichier.exists(), equalTo(true));
    }


    @After
    public void nettoyage() {
        new File("base").delete();
    }
}