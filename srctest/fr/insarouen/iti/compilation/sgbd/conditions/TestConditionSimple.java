package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestConditionSimple{

    private Tuple tupleHero(){
        Tuple hero=new Tuple();

        hero.setValeur("Hero.nom",new Chaine("Batman"));
        hero.setValeur("Hero.puissance",new Nombre(100));
        hero.setValeur("Hero.age",new Nombre(40));

        return hero;
    }

    @Test
    public void testSuperieurVrai(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testSuperieurFaux(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
            new OperandeValeur(new Nombre(200)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testInferieurVrai(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testInferieurFaux(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(30)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

    @Test
    public void testEgaliteChaineVraie(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(true));
    }

    @Test
    public void testEgaliteChaineFausse(){

        Tuple tuple = tupleHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(tuple),is(false));
    }

}

