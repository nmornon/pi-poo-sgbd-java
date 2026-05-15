package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.conditions.*;
import fr.insarouen.iti.compilation.sgbd.moteur.*;

public class TestExpressionLogique {

    private Tuple tupleHero(){
        Tuple hero=new Tuple();

        hero.setValeur("Hero.nom",new Chaine("Batman"));
        hero.setValeur("Hero.puissance",new Nombre(100));
        hero.setValeur("Hero.age",new Nombre(40));

        return hero;
    }

    private Tuple tuplePersonneVoiture(){
        Tuple tuple = new Tuple();

        tuple.setValeur("Personne.id",new Cle(1));
        tuple.setValeur("Personne.nom",new Chaine("Bob"));
        tuple.setValeur("Personne.age",new Nombre(20));

        tuple.setValeur("Voiture.id",new Cle(10));
        tuple.setValeur("Voiture.marque",new Chaine("BMW"));
        tuple.setValeur("Voiture.proprietaire",new Nombre(1));

        return tuple;

    }

    @Test
    public void testExpressionAvecAndVraie(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testExpressionAvecAndFausse(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(false));
    }

    @Test
    public void testExpressionAvecOrVraie(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testExpressionAvecOrVFausse(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(false));
    }

    @Test
    public void testExpressionAvecTroisConditionsVrai(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testExpressionAvecTroisConditionsFaux(){

        Tuple tuple = tupleHero();

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

        assertThat(expression.evaluer(tuple),is(false));
    }

    @Test
    public void testExpressionAvecDeuxTablesVraie(){

        Tuple tuple = tuplePersonneVoiture();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Personne","id"),
            new OperandeAttribut("Voiture","proprietaire"),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Personne","age"),
            new OperandeValeur(new Nombre(18)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut("Voiture","marque"),
            new OperandeValeur(new Chaine("BMW")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c2);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c3);

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testExpressionAvecDeuxTablesFausse(){

        Tuple tuple = tuplePersonneVoiture();

        ConditionSimple c1= new ConditionSimple(
            new OperandeAttribut("Personne","id"),
            new OperandeAttribut("Voiture","proprietaire"),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeAttribut("Personne","age"),
            new OperandeValeur(new Nombre(18)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeAttribut("Voiture","marque"),
            new OperandeValeur(new Chaine("Citroen")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c2);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(c3);

        assertThat(expression.evaluer(tuple),is(false));
    }


}
