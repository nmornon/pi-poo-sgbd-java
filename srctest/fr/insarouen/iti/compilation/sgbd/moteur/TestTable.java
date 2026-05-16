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
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeAttribut;
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeValeur;
import fr.insarouen.iti.compilation.sgbd.exceptions.AttributDejaExistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.AttributInexistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.RelationSansAttributException;

public class TestTable {
    public static BaseDeDonnees baseDeDonnees;
    public static Table table;

    @BeforeClass
    public static void initialiser() {
        TestRelation.initialiser();
        
        baseDeDonnees = new BaseDeDonnees("bdd", 4);

        table = baseDeDonnees.creerTable("personnes", List.of(
            new Attribut("id", Type.SERIAL),
            new Attribut("nom", Type.VARCHAR),
            new Attribut("age", Type.INT)
        ), List.of(
            new Tuple().setValeur("id", new Cle(1))
                       .setValeur("nom", new Chaine("Alice"))
                       .setValeur("age", new Nombre(25)),
            new Tuple().setValeur("id", new Cle(2))
                       .setValeur("nom", new Chaine("Bob"))
                       .setValeur("age", new Nombre(30)),
            new Tuple().setValeur("id", new Cle(3))
                       .setValeur("nom", new Chaine("Charly"))
                       .setValeur("age", new Nombre(35))
        ));
    }

    @Before
    public void avantTout(){
        initialiser();
    }
    
    @Test
    public void test_constructeur() {
        assertThat(table.getIdentifiant(), equalTo("personnes"));
        assertThat(table.hasAttribut("id"), is(true));
        assertThat(table.hasAttribut("nom"), is(true));
        assertThat(table.hasAttribut("age"), is(true));
    }
    
    @Test(expected = RelationSansAttributException.class)
    public void test_constructeur_attributsVide() {
        new Table(baseDeDonnees, "table", Collections.emptyList());
    }

    @Test(expected = AttributDejaExistantException.class)
    public void test_constructeur_avecAttributEnDouble() {
        Collection<Attribut> attributs = List.of(
            new Attribut("id", Type.SERIAL),
            new Attribut("id", Type.SERIAL)
        );
        new Table(baseDeDonnees, "table", attributs);
    }

    @Test(expected = AttributInexistantException.class)
    public void test_constructeur_tupleAvecAttributInexistant() {
        Collection<Attribut> attributs = List.of(
            new Attribut("id", Type.SERIAL)
        );
        Collection<Tuple> tuples = List.of(
            new Tuple().setValeur("attribut_inexistant", new Nombre(0))
        );
        new Table(baseDeDonnees, "table", attributs, tuples);
    }

    @Test
    public void test_inserer() {
        Tuple tuple1 = new Tuple()
                .setValeur("nom", new Chaine("Delestre"))
                .setValeur("age", new Nombre(42));

        Tuple tuple2 = new Tuple()
                .setValeur("nom", new Chaine("Malandin"))
                .setValeur("age", new Nombre(42 * 4));

        table.inserer(tuple1);
        table.inserer(tuple2);

        assertThat(table.getTuples().size(), is(5));
        assertThat(tuple1.getValeur("id"), equalTo(new Cle(4)));
        assertThat(tuple2.getValeur("id"), equalTo(new Cle(5)));
    }

    @Test(expected = AttributInexistantException.class)
    public void test_inserer_tupleAvecAttributInexistant() {
        table.inserer(new Tuple().setValeur("poids", new Nombre(70)));
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
            new OperandeAttribut("age"),
            new OperandeValeur(new Nombre(30)),
            Comparateur.SUPERIEUR
        ));
        assertThat(table.supprimer(condition), is(1));
        assertThat(table.getTuples().size(), is(2));
    }
}
