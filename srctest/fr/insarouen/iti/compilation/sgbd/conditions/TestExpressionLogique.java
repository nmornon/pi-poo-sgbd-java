package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestExpressionLogique {

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
    public void testExpressionAvecAndVraie(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c2);

        assertThat(expression.evaluer(contexte),is(true));
    }

    @Test
    public void testExpressionAvecAndFausse(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c2);

        assertThat(expression.evaluer(contexte),is(false));
    }

    @Test
    public void testExpressionAvecOrVraie(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.OR);
        expression.ajouterCondition(c2);

        assertThat(expression.evaluer(contexte),is(true));
    }

    @Test
    public void testExpressionAvecOrVFausse(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(10)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.OR);
        expression.ajouterCondition(c2);

        assertThat(expression.evaluer(contexte),is(false));
    }

    @Test
    public void testExpressionAvecTroisConditionsVrai(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("puissance")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c2);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c3);

        assertThat(expression.evaluer(contexte),is(true));
    }

    @Test
    public void testExpressionAvecTroisConditionsFaux(){

        Map<Identifiant,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("nom")),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("puissance")),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut(new Identifiant("Hero"),new Identifiant("age")),
            new OperandeValeur(new Nombre(30)),
            Comparateur.INFERIEUR
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.OR);
        expression.ajouterCondition(c2);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c3);

        assertThat(expression.evaluer(contexte),is(false));
    }
}