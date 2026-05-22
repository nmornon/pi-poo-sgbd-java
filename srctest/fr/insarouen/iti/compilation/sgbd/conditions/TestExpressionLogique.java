package fr.insarouen.iti.compilation.sgbd.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Chaine;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Cle;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Entier;

public class TestExpressionLogique {

    private Tuple tupleHero(){
        Tuple hero=new Tuple();

        hero.setValeur("Hero.nom",new Chaine("Batman"));
        hero.setValeur("Hero.puissance",new Entier(100));
        hero.setValeur("Hero.age",new Entier(40));

        return hero;
    }

    private Tuple tuplePersonneVoiture(){
        Tuple tuple = new Tuple();

        tuple.setValeur("Personne.id",new Cle(1));
        tuple.setValeur("Personne.nom",new Chaine("Bob"));
        tuple.setValeur("Personne.age",new Entier(20));

        tuple.setValeur("Voiture.id",new Cle(10));
        tuple.setValeur("Voiture.marque",new Chaine("BMW"));
        tuple.setValeur("Voiture.proprietaire",new Entier(1));

        return tuple;

    }

    @Test
    public void testExpressionAvecAndVraie(){

        Tuple tuple = tupleHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
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
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
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
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
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
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(10)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
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
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(50)),
            Comparateur.INFERIEUR
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeColonne("Hero","puissance"),
            new OperandeValeur(new Entier(50)),
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
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Hero","puissance"),
            new OperandeValeur(new Entier(50)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(30)),
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
            new OperandeColonne("Personne","id"),
            new OperandeColonne("Voiture","proprietaire"),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Personne","age"),
            new OperandeValeur(new Entier(18)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeColonne("Voiture","marque"),
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
            new OperandeColonne("Personne","id"),
            new OperandeColonne("Voiture","proprietaire"),
            Comparateur.EGAL
        );

        ConditionSimple c2= new ConditionSimple(
            new OperandeColonne("Personne","age"),
            new OperandeValeur(new Entier(18)),
            Comparateur.SUPERIEUR
        );

        ConditionSimple c3= new ConditionSimple(
            new OperandeColonne("Voiture","marque"),
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

    @Test
    public void testExpressionAvecUneSeuleConditionVraie(){

        Tuple tuple = tupleHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testExpressionAvecUneSeuleConditionFausse(){

        Tuple tuple = tupleHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Joker")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);

        assertThat(expression.evaluer(tuple),is(false));
    }

    @Test
    public void testExpressionAvecConnecteurEnTropIgnore(){

        Tuple tuple = tupleHero();

        ConditionSimple c1= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(c1);
        expression.ajouterConnecteur(OperateurLogique.AND);

        assertThat(expression.evaluer(tuple),is(true));
    }

    @Test
    public void testEvaluationDeGaucheADroiteSansPrioriteAnd(){

        Tuple tuple = tupleHero();

        ConditionSimple vrai= new ConditionSimple(
            new OperandeColonne("Hero","nom"),
            new OperandeValeur(new Chaine("Batman")),
            Comparateur.EGAL
        );

        ConditionSimple faux1= new ConditionSimple(
            new OperandeColonne("Hero","age"),
            new OperandeValeur(new Entier(10)),
            Comparateur.INFERIEUR
        );

        ConditionSimple faux2= new ConditionSimple(
            new OperandeColonne("Hero","puissance"),
            new OperandeValeur(new Entier(200)),
            Comparateur.SUPERIEUR
        );

        ExpressionLogique expression= new ExpressionLogique();
        expression.ajouterCondition(vrai);
        expression.ajouterConnecteur(OperateurLogique.OR);
        expression.ajouterCondition(faux1);
        expression.ajouterConnecteur(OperateurLogique.AND);
        expression.ajouterCondition(faux2);

        assertThat(expression.evaluer(tuple),is(false));
    }


}
