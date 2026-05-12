package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestConditionSimple{

    private Map<Identifiant,Tuple> contextHero(){
        Tuple hero=new Tuple();

        hero.set(new Identifiant("nom"),new Chaine("Batman"));
        hero.set(new Identifiant("puissance"),new Nombre(100));
        hero.set(new Identifiant("age"),new Nombre(40));

        Map<Identifiant,Tuple> contexte = new HashMap<>();

        contexte.put(new Identifiant("Hero"),hero);

        return contexte;
    }

    @Test
    public void testSuperieurVrai(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("puissance")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testSuperieurFaux(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("puissance")),
            new OperandeValeur(new Nombre(200)),
            Comparateur.SUPERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

    @Test
    public void testInferieurVrai(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testInferieurFaux(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(30)),
            Comparateur.INFERIEUR
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

    @Test
    public void testEgaliteChaineVraie(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(contexte),is(true));
    }

    @Test
    public void testEgaliteChaineFausse(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple condition= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );
        
        assertThat(condition.evaluer(contexte),is(false));
    }

}

