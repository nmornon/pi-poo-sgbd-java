package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;
import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneInexistantException;

public class TestConditionSimple{

    private Tuple tupleHero(){
        Tuple hero=new Tuple();

        hero.setValeur("Hero.nom",new Chaine("Batman"));
        hero.setValeur("Hero.puissance",new Entier(100));
        hero.setValeur("Hero.age",new Entier(40));

        return hero;
    }

    @Test
    public void testSuperieurVrai(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","puissance"),
            new OperandeValeur(new Entier(50)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testSuperieurFaux(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","puissance"),
            new OperandeValeur(new Entier(200)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testInferieurVrai(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(50)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testInferieurFaux(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(30)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testEgaliteChaineVraie(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testEgaliteChaineFausse(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testEgaliteEntierVraie(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(40)),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testEgaliteEntierFausse(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(41)),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testComparaisonColonneColonneVraie(){

        Tuple tuple = tupleHero();
        tuple.setValeur("Hero.ageBis", new Entier(40));

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeColonne("Hero","ageBis"),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test(expected = ColonneInexistantException.class)
    public void testColonneInexistante(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeColonne("Hero","taille"),
            new OperandeValeur(new Entier(180)),
            Comparateur.EGAL
        );
        
        condition.evaluer(tuple);
    }

}

