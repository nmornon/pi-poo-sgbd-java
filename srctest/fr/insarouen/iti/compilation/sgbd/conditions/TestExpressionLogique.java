package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestExpressionLogique {

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
    public void testExpressionAvecAndVraie(){

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
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

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
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

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
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

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(10)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
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

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
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

        Map<String,Tuple> contexte =contextHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Hero","puissance"),
            new OperandeValeur(new Nombre(50)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut("Hero","age"),
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