package fr.insarouen.iti.compilation.sgbd.persistance;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.insarouen.iti.compilation.sgbd.moteur.BaseDeDonnees;


public class TestPersistance {
    private BaseDeDonnees bdd;
    private GestionnairePersistance gestionnaire;
    private final String NOM_FICHIER="base_test";

    @Before
    public void avantTest() {
        bdd = new BaseDeDonnees(NOM_FICHIER);
        gestionnaire = new GestionnairePersistance();
    }

    @Test
    public void test_chargerFichierInexistant() throws IOException{
        assertThat(gestionnaire.charger("fichier_inexistant"), equalTo(new BaseDeDonnees("fichier_inexistant")));
    }

    @Test
    public void test_sauvegarde_et_chargement() throws IOException{
        bdd.setEstOuverte(false);
        gestionnaire.sauvegarder(bdd);

        BaseDeDonnees test = gestionnaire.charger(NOM_FICHIER);

        assertThat(test.getIdentifiant(), equalTo(NOM_FICHIER));
    }

    @Test
    public void test_chargement_estOuverte() throws IOException{
        bdd.setEstOuverte(false);
        gestionnaire.sauvegarder(bdd);

        BaseDeDonnees test = gestionnaire.charger(NOM_FICHIER);

        assertThat(test.getEstOuverte(), equalTo(true));
    }

    @Test
    public void test_creation_fichier() throws IOException{
        gestionnaire.sauvegarder(bdd);

        File fichier = new File(NOM_FICHIER);

        assertThat(fichier.exists(), equalTo(true));
    }

    @Test(expected = IOException.class)
    public void test_fichier_corrompu() throws IOException {

        FileWriter writer = new FileWriter("fichier_corrompu");
        writer.write("contenu invalide");
        writer.close();

        gestionnaire.charger("fichier_corrompu");
    }

    @Test
    public void test_ecrasement_sauvegarde() throws IOException {

        bdd.setEstOuverte(false);
        gestionnaire.sauvegarder(bdd);

        bdd.setEstOuverte(true);
        gestionnaire.sauvegarder(bdd);

        BaseDeDonnees test = gestionnaire.charger(NOM_FICHIER);

        assertThat(test.getEstOuverte(), equalTo(true));
    }

    @After
    public void nettoyage() {
        new File(NOM_FICHIER).delete();
        new File("fichier_corrompu").delete();
        new File("fichier_inexistant").delete();
    }
}