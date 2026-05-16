package fr.insarouen.iti.compilation.sgbd.moteur;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.Comparateur;
import fr.insarouen.iti.compilation.sgbd.conditions.ConditionSimple;
import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeColonne;
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeValeur;
import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneDejaExistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneInexistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.RelationSansColonneException;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;

public class TestTable {
    public static BaseDeDonnees baseDeDonnees;
    public static Table table;

    @BeforeClass
    public static void initialiser() {
        TestRelation.initialiser();
        
        baseDeDonnees = new BaseDeDonnees("bdd", 4);

        table = baseDeDonnees.creerTable("personnes", List.of(
            new Colonne("id", Type.SERIAL),
            new Colonne("nom", Type.VARCHAR),
            new Colonne("age", Type.INT)
        ), List.of(
            new Tuple().setValeur("id", new Cle(1))
                       .setValeur("nom", new Chaine("Alice"))
                       .setValeur("age", new Entier(25)),
            new Tuple().setValeur("id", new Cle(2))
                       .setValeur("nom", new Chaine("Bob"))
                       .setValeur("age", new Entier(30)),
            new Tuple().setValeur("id", new Cle(3))
                       .setValeur("nom", new Chaine("Charly"))
                       .setValeur("age", new Entier(35))
        ));
    }

    @Before
    public void avantTout(){
        initialiser();
    }
    
    @Test
    public void test_constructeur() {
        assertThat(table.getIdentifiant(), equalTo("personnes"));
        assertThat(table.hasColonne("id"), is(true));
        assertThat(table.hasColonne("nom"), is(true));
        assertThat(table.hasColonne("age"), is(true));
    }
    
    @Test(expected = RelationSansColonneException.class)
    public void test_constructeur_colonnesVide() {
        new Table(baseDeDonnees, "table", Collections.emptyList());
    }

    @Test(expected = ColonneDejaExistantException.class)
    public void test_constructeur_avecColonneEnDouble() {
        Collection<Colonne> colonnes = List.of(
            new Colonne("id", Type.SERIAL),
            new Colonne("id", Type.SERIAL)
        );
        new Table(baseDeDonnees, "table", colonnes);
    }

    @Test(expected = ColonneInexistantException.class)
    public void test_constructeur_tupleAvecColonneInexistant() {
        Collection<Colonne> colonnes = List.of(
            new Colonne("id", Type.SERIAL)
        );
        Collection<Tuple> tuples = List.of(
            new Tuple().setValeur("colonne_inexistant", new Entier(0))
        );
        new Table(baseDeDonnees, "table", colonnes, tuples);
    }

    @Test
    public void test_inserer() {
        Tuple tuple1 = new Tuple()
                .setValeur("nom", new Chaine("Delestre"))
                .setValeur("age", new Entier(42));

        Tuple tuple2 = new Tuple()
                .setValeur("nom", new Chaine("Malandin"))
                .setValeur("age", new Entier(42 * 4));

        table.inserer(tuple1);
        table.inserer(tuple2);

        assertThat(table.getTuples().size(), is(5));
        assertThat(tuple1.getValeur("id"), equalTo(new Cle(4)));
        assertThat(tuple2.getValeur("id"), equalTo(new Cle(5)));
    }

    @Test(expected = ColonneInexistantException.class)
    public void test_inserer_tupleAvecColonneInexistant() {
        table.inserer(new Tuple().setValeur("poids", new Entier(70)));
    }

    @Test
    public void test_supprimer_avecCondition() {
        int nbTuple = table.getTuples().size();

        assertThat(table.supprimer(), is(nbTuple));
        assertThat(table.getTuples().size(), is(0));
    }

    @Test
    public void test_supprimer_tousLesTuples() {
        ExpressionLogique condition = new ExpressionLogique();
        condition.ajouterCondition(new ConditionSimple(
            new OperandeColonne("age"),
            new OperandeValeur(new Entier(30)),
            Comparateur.SUPERIEUR
        ));
        assertThat(table.supprimer(condition), is(1));
        assertThat(table.getTuples().size(), is(2));
    }
}
