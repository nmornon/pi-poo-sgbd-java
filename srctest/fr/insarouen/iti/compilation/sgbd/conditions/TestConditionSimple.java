package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestConditionSimple{

    private Map<String,Tuple> contextHero(){
        Tuple hero=new Tuple();

        hero.setValeur("nom",new Chaine("Batman"));
        hero.setValeur("puissance",new Nombre(100));
        hero.setValeur("age",new Nombre(40));

        Map<String,Tuple> contexte = new HashMap<>();

        contexte.put("Hero",hero);

        return contexte;
    }

    @Test
    public void testSuperieurVrai(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testSuperieurFaux(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
            new OperandeValeur(new Nombre(200)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

    @Test
    public void testInferieurVrai(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testInferieurFaux(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(30)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

    @Test
    public void testEgaliteChaineVraie(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testEgaliteChaineFausse(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

}

