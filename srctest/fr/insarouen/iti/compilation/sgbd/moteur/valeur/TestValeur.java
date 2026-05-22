package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;
import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

import org.junit.Test;

public class TestValeur{

    @Test
    public void test_chaine_equals(){
        Chaine chaine1 = new Chaine("Batman");
        Chaine chaine2 = new Chaine("Batman");

        assertThat(chaine1,equalTo(chaine2));
    }

    @Test
    public void test_equals(){
        Chaine chaine1 = new Chaine("Batman");
        Entier entier1 = new Entier(2);

        assertThat(chaine1.equals(entier1),is(false));
    }

    @Test
    public void testCompareToEntier() {

        Valeur petit = new Entier(5);
        Valeur grand = new Entier(10);
        Valeur meme = new Entier(5);

        assertThat(grand.compareTo(petit) > 0, is(true));
        assertThat(petit.compareTo(grand) < 0, is(true));
        assertThat(petit.compareTo(meme), is(0));
    }

    @Test
    public void testCompareToChaine() {

        Valeur a = new Chaine("Batman");
        Valeur b = new Chaine("Joker");
        Valeur meme = new Chaine("Batman");

        assertThat(a.compareTo(b) < 0, is(true));
        assertThat(b.compareTo(a) > 0, is(true));
        assertThat(a.compareTo(meme), is(0));
    }

    @Test
    public void testCompareToCle() {

        Valeur petiteCle = new Cle(1);
        Valeur grandeCle = new Cle(5);
        Valeur memeCle = new Cle(1);

        assertThat(grandeCle.compareTo(petiteCle) > 0, is(true));
        assertThat(petiteCle.compareTo(grandeCle) < 0, is(true));
        assertThat(petiteCle.compareTo(memeCle), is(0));
    }

    @Test
    public void testCompareToEntierEtCle() {

        Valeur entier = new Entier(10);
        Valeur cle = new Cle(10);

        assertThat(entier.compareTo(cle), is(0));
        assertThat(cle.compareTo(entier), is(0));
    }

    @Test(expected = TypeInvalideException.class)
    public void testComparaisonInvalideEntierChaine() {

        Valeur entier = new Entier(10);
        Valeur chaine = new Chaine("abc");

        entier.compareTo(chaine);
    }

}