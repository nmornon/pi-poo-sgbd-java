package fr.insarouen.iti.compilation.sgbd.moteur;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.equalTo;
// import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.insarouen.iti.compilation.sgbd.exceptions.AttributDejaExistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.AttributInexistantException;
import fr.insarouen.iti.compilation.sgbd.exceptions.ProduitCartesienException;
import fr.insarouen.iti.compilation.sgbd.exceptions.RelationSansAttributException;
import fr.insarouen.iti.compilation.sgbd.exceptions.SelectionException;
import fr.insarouen.iti.compilation.sgbd.conditions.ExpressionLogique;
import fr.insarouen.iti.compilation.sgbd.conditions.ConditionSimple;
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeAttribut;
import fr.insarouen.iti.compilation.sgbd.conditions.OperandeValeur;
import fr.insarouen.iti.compilation.sgbd.conditions.Comparateur;
import fr.insarouen.iti.compilation.sgbd.conditions.OperateurLogique;


public class TestRelation {
    public static Relation relationBases;
    public static Relation relationHeroes;
    public static Relation relationGadgets;
    public static Collection<Attribut> attributsHeroes = new ArrayList<>();
    public static Collection<Attribut> attributsBases = new ArrayList<>();
    public static Collection<Attribut> attributsGadgets = new ArrayList<>();
    public static Collection<Tuple> tuplesHeroes = new ArrayList<>();
    public static Collection<Tuple> tuplesBases = new ArrayList<>();
    public static Collection<Tuple> tuplesGadgets = new ArrayList<>();

    @BeforeClass
    public static void initialiser() {
        TestRelation.attributsBases.clear();
        TestRelation.attributsBases.add(new Attribut("id", Type.SERIAL));
        TestRelation.attributsBases.add(new Attribut("nom", Type.VARCHAR));
        TestRelation.attributsBases.add(new Attribut("ville", Type.VARCHAR));

        TestRelation.attributsHeroes.clear();
        TestRelation.attributsHeroes.add(new Attribut("id", Type.SERIAL));
        TestRelation.attributsHeroes.add(new Attribut("nom", Type.VARCHAR));
        TestRelation.attributsHeroes.add(new Attribut("alias", Type.VARCHAR));
        TestRelation.attributsHeroes.add(new Attribut("base_id", Type.INT));

        TestRelation.attributsGadgets.clear();
        TestRelation.attributsGadgets.add(new Attribut("id", Type.SERIAL));
        TestRelation.attributsGadgets.add(new Attribut("nom", Type.VARCHAR));
        TestRelation.attributsGadgets.add(new Attribut("hero_id", Type.INT));
        TestRelation.attributsGadgets.add(new Attribut("puissance", Type.INT));

        TestRelation.tuplesBases.clear();
        TestRelation.tuplesBases.add(new Tuple().setValeur("id", new Cle(1)).setValeur("nom", new Chaine("BureauInfo")).setValeur("ville", new Chaine("ITICity")));
        TestRelation.tuplesBases.add(new Tuple().setValeur("id", new Cle(2)).setValeur("nom", new Chaine("BureauElec")).setValeur("ville", new Chaine("ITICity")));
        TestRelation.tuplesBases.add(new Tuple().setValeur("id", new Cle(3)).setValeur("nom", new Chaine("BureauML")).setValeur("ville", new Chaine("ITICity")));
        TestRelation.tuplesBases.add(new Tuple().setValeur("id", new Cle(4)).setValeur("nom", new Chaine("BureauDirectionITI")).setValeur("ville", new Chaine("ITICity")));
        TestRelation.tuplesBases.add(new Tuple().setValeur("id", new Cle(5)).setValeur("nom", new Chaine("BureauDirectionLITIS")).setValeur("ville", new Chaine("LITISCity")));
        
        TestRelation.tuplesHeroes.clear();
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(6)).setValeur("nom", new Chaine("Nicola Malandain")).setValeur("alias", new Chaine("Batman")).setValeur("base_id", new Nombre(1)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(7)).setValeur("nom", new Chaine("Nicolas Delestre")).setValeur("alias", new Chaine("Bikeman")).setValeur("base_id", new Nombre(1)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(8)).setValeur("nom", new Chaine("Florian Yger")).setValeur("alias", new Chaine("Baldman")).setValeur("base_id", new Nombre(1)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(9)).setValeur("nom", new Chaine("Geraldine Del Mondo")).setValeur("alias", new Chaine("Batgirl")).setValeur("base_id", new Nombre(4)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(10)).setValeur("nom", new Chaine("Benoit Gauzere")).setValeur("alias", new Chaine("BGman")).setValeur("base_id", new Nombre(3)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(11)).setValeur("nom", new Chaine("Clement Chatelain")).setValeur("alias", new Chaine("MLman")).setValeur("base_id", new Nombre(5)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(12)).setValeur("nom", new Chaine("Hind Laghmara")).setValeur("alias", new Chaine("Resistorwoman")).setValeur("base_id", new Nombre(2)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(13)).setValeur("nom", new Chaine("Laurent Vercouter")).setValeur("alias", new Chaine("Aquaman")).setValeur("base_id", new Nombre(5)));
        TestRelation.tuplesHeroes.add(new Tuple().setValeur("id", new Cle(14)).setValeur("nom", new Chaine("Robin Condat")).setValeur("alias", new Chaine("Robin")).setValeur("base_id", new Nombre(2)));

        TestRelation.tuplesGadgets.clear();
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(15)).setValeur("nom", new Chaine("Batarang")).setValeur("hero_id", new Nombre(6)).setValeur("puissance", new Nombre(50)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(16)).setValeur("nom", new Chaine("Grappin")).setValeur("hero_id", new Nombre(6)).setValeur("puissance", new Nombre(30)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(17)).setValeur("nom", new Chaine("Velo de course")).setValeur("hero_id", new Nombre(7)).setValeur("puissance", new Nombre(40)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(18)).setValeur("nom", new Chaine("Casque Aero")).setValeur("hero_id", new Nombre(7)).setValeur("puissance", new Nombre(20)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(19)).setValeur("nom", new Chaine("Bouclier Solaire")).setValeur("hero_id", new Nombre(8)).setValeur("puissance", new Nombre(60)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(20)).setValeur("nom", new Chaine("Perruque de camouflage")).setValeur("hero_id", new Nombre(8)).setValeur("puissance", new Nombre(10)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(21)).setValeur("nom", new Chaine("Ordinateur portable")).setValeur("hero_id", new Nombre(9)).setValeur("puissance", new Nombre(80)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(22)).setValeur("nom", new Chaine("Moto Batgirl")).setValeur("hero_id", new Nombre(9)).setValeur("puissance", new Nombre(70)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(23)).setValeur("nom", new Chaine("Lunettes de BG")).setValeur("hero_id", new Nombre(10)).setValeur("puissance", new Nombre(90)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(24)).setValeur("nom", new Chaine("Parfum de charisme")).setValeur("hero_id", new Nombre(10)).setValeur("puissance", new Nombre(100)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(25)).setValeur("nom", new Chaine("Neural Network Optimizer")).setValeur("hero_id", new Nombre(11)).setValeur("puissance", new Nombre(85)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(26)).setValeur("nom", new Chaine("GPU Overclocker")).setValeur("hero_id", new Nombre(11)).setValeur("puissance", new Nombre(95)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(27)).setValeur("nom", new Chaine("Multimetre")).setValeur("hero_id", new Nombre(12)).setValeur("puissance", new Nombre(45)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(28)).setValeur("nom", new Chaine("Resistance 10k Ohms")).setValeur("hero_id", new Nombre(12)).setValeur("puissance", new Nombre(15)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(29)).setValeur("nom", new Chaine("Trident")).setValeur("hero_id", new Nombre(13)).setValeur("puissance", new Nombre(88)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(30)).setValeur("nom", new Chaine("Bulle d''eau")).setValeur("hero_id", new Nombre(13)).setValeur("puissance", new Nombre(55)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(31)).setValeur("nom", new Chaine("Bo staff")).setValeur("hero_id", new Nombre(14)).setValeur("puissance", new Nombre(35)));
        TestRelation.tuplesGadgets.add(new Tuple().setValeur("id", new Cle(32)).setValeur("nom", new Chaine("Bat-cycle")).setValeur("hero_id", new Nombre(14)).setValeur("puissance", new Nombre(65)));

        TestRelation.relationBases = new Relation("bases", TestRelation.attributsBases, TestRelation.tuplesBases);
        TestRelation.relationHeroes = new Relation("heroes", TestRelation.attributsHeroes, TestRelation.tuplesHeroes);
        TestRelation.relationGadgets = new Relation("gadgets", TestRelation.attributsGadgets, TestRelation.tuplesGadgets);
    }

    @Test(expected = RelationSansAttributException.class)
    public void test_constructeur_avecAttributsVide() {
        new Relation("relation", new ArrayList<>());
    }

    @Test(expected = AttributDejaExistantException.class)
    public void test_constructeur_avecAttributEnDouble() {
        Collection<Attribut> attributs = List.of(
            new Attribut("id", Type.SERIAL),
            new Attribut("id", Type.SERIAL)
        );
        new Relation("relation", attributs);
    }

    @Test(expected = AttributInexistantException.class)
    public void test_constructeur_tupleAvecAttributInexistant() {
        Collection<Attribut> attributs = List.of(
            new Attribut("id", Type.SERIAL)
        );
        Collection<Tuple> tuples = List.of(
            new Tuple().setValeur("attribut_inexistant", new Nombre(0))
        );
        new Relation("relation", attributs, tuples);
    }

    @Test
    public void test_getIdentifiant() {
        assertThat(relationBases.getIdentifiant(), equalTo("bases"));
        assertThat(relationHeroes.getIdentifiant(), equalTo("heroes"));
        assertThat(relationGadgets.getIdentifiant(), equalTo("gadgets"));
    }

    @Test
    public void test_hasAttribut() {
        assertThat(relationBases.hasAttribut("id"), is(true));
        assertThat(relationBases.hasAttribut("attribut_inexistant"), is(false));
    }

    @Test
    public void test_getAttribut_attributExistant() {
        assertThat(relationBases.getAttribut("id"), equalTo(new Attribut("id", Type.SERIAL)));
    }

    @Test
    public void test_getAttribut_attributInexistant() {
        assertThat(relationBases.getAttribut("attribut_inexistant"), nullValue());
    }

    @Test
    public void test_getTuples() {
        assertThat(relationBases.getTuples(), equalTo(tuplesBases));
        assertThat(relationHeroes.getTuples(), equalTo(tuplesHeroes));
        assertThat(relationGadgets.getTuples(), equalTo(tuplesGadgets));
    }

    // @Test
    // public void test_getAttributs() {
    //     assertThat(relationBases.getAttributs(), containsInAnyOrder(attributsBases.toArray()));
    //     assertThat(relationHeroes.getAttributs(), containsInAnyOrder(attributsHeroes.toArray()));
    //     assertThat(relationGadgets.getAttributs(), containsInAnyOrder(attributsGadgets.toArray()));
    // }

    @Test
    public void test_selectionner() {
        Relation relation = relationBases.selectionner(Set.of("nom", "ville"));
        
        assertThat(relation.hasAttribut("id"), is(false));
        assertThat(relation.hasAttribut("nom"), is(true));
        assertThat(relation.hasAttribut("ville"), is(true));
    }

    @Test(expected = SelectionException.class)
    public void test_selectionner_attributInexistant() {
        relationBases.selectionner(Set.of("attribut_inexistant"));
    }

    @Test
    public void test_produitCartesien_avecDeuxRelations() {
        Relation produit = relationBases.produitCartesien(relationHeroes);
        
        assertThat(produit.getIdentifiant(), equalTo("bases_heroes"));
        assertThat(produit.hasAttribut("bases.id"), is(true));
        assertThat(produit.hasAttribut("bases.nom"), is(true));
        assertThat(produit.hasAttribut("bases.ville"), is(true));
        assertThat(produit.hasAttribut("heroes.id"), is(true));
        assertThat(produit.hasAttribut("heroes.nom"), is(true));
        assertThat(produit.hasAttribut("heroes.alias"), is(true));
        assertThat(produit.hasAttribut("heroes.base_id"), is(true));
    }

    @Test
    public void test_produitCartesien_avecTroisRelations() {
        Relation produit = relationBases.produitCartesien(List.of(relationHeroes, relationGadgets));
        
        assertThat(produit.getIdentifiant(), equalTo("bases_heroes_gadgets"));
        assertThat(produit.hasAttribut("bases.id"), is(true));
        assertThat(produit.hasAttribut("bases.nom"), is(true));
        assertThat(produit.hasAttribut("bases.ville"), is(true));

        assertThat(produit.hasAttribut("heroes.id"), is(true));
        assertThat(produit.hasAttribut("heroes.nom"), is(true));
        assertThat(produit.hasAttribut("heroes.alias"), is(true));
        assertThat(produit.hasAttribut("heroes.base_id"), is(true));

        assertThat(produit.hasAttribut("gadgets.id"), is(true));
        assertThat(produit.hasAttribut("gadgets.nom"), is(true));
        assertThat(produit.hasAttribut("gadgets.hero_id"), is(true));
        assertThat(produit.hasAttribut("gadgets.puissance"), is(true));
    }

    @Test
    public void test_produitCartesien_avecLesDeuxMemesRelationsAvecAlias() {
        Relation relation1 = relationBases.alias("B1");
        Relation relation2 = relationBases.alias("B2");
        Relation produit = relation1.produitCartesien(relation2);
        
        assertThat(produit.getIdentifiant(), equalTo("B1_B2"));
        assertThat(produit.hasAttribut("B1.id"), is(true));
        assertThat(produit.hasAttribut("B1.nom"), is(true));
        assertThat(produit.hasAttribut("B1.ville"), is(true));
        assertThat(produit.hasAttribut("B2.id"), is(true));
        assertThat(produit.hasAttribut("B2.nom"), is(true));
        assertThat(produit.hasAttribut("B2.ville"), is(true));
    }

    @Test(expected = ProduitCartesienException.class)
    public void test_produitCartesien_avecLesDeuxMemesRelationsSansAlias() {
        relationBases.produitCartesien(relationBases);
    }

    @Test
    public void test_projeter_apresProduitCartesien(){
        Relation produit=relationBases.produitCartesien(relationHeroes);

        ExpressionLogique condition = new ExpressionLogique();
        condition.ajouterCondition( new ConditionSimple(
                                        new OperandeAttribut("bases","id"),
                                        new OperandeAttribut("heroes","base_id"),
                                        Comparateur.EGAL

        
        ));

        Relation resultat=produit.projeter(condition);

        assertThat(resultat.getTuples().size(),is(9));

        assertThat(resultat.hasAttribut("bases.id"),is(true));
        assertThat(resultat.hasAttribut("bases.nom"),is(true));
        assertThat(resultat.hasAttribut("heroes.nom"),is(true));
        assertThat(resultat.hasAttribut("heroes.alias"),is(true));
        assertThat(resultat.hasAttribut("gadgets.nom"),is(false));
    }

    @Test
    public void test_serial_genereAutomatiquement() {
        Tuple premierTuple = relationBases.getTuples().iterator().next();

        assertThat(premierTuple.hasIdentifiant("id"), is(true));
        assertThat(premierTuple.getValeur("id").getValeur(), is(1));
    }

    @Test
    public void test_produitCartesien_nombreTuples() {
        Relation produit = relationBases.produitCartesien(relationHeroes);

        assertThat(produit.getTuples().size(), is(45));
    }

    @Test
    public void test_produitCartesien_troisRelations_nombreTuples() {
        Relation produit = relationBases.produitCartesien(List.of(relationHeroes, relationGadgets));

        assertThat(produit.getTuples().size(), is(810));
    }

    @Test
    public void test_projeter_basesHeroes() {
        Relation produit = relationBases.produitCartesien(relationHeroes);

        ExpressionLogique condition = new ExpressionLogique();
        condition.ajouterCondition(new ConditionSimple(
            new OperandeAttribut("bases", "id"),
            new OperandeAttribut("heroes", "base_id"),
            Comparateur.EGAL
        ));

        Relation resultat = produit.projeter(condition);

        assertThat(resultat.getTuples().size(), is(9));
        assertThat(resultat.hasAttribut("bases.nom"), is(true));
        assertThat(resultat.hasAttribut("heroes.alias"), is(true));
        assertThat(resultat.hasAttribut("gadgets.nom"), is(false));
    }

    @Test
    public void test_projeter_conditionImpossible() {
        Relation produit = relationBases.produitCartesien(relationHeroes);

        ExpressionLogique condition = new ExpressionLogique();
        condition.ajouterCondition(new ConditionSimple(
            new OperandeAttribut("bases", "id"),
            new OperandeValeur(new Nombre(999)),
            Comparateur.EGAL
        ));

        Relation resultat = produit.projeter(condition);

        assertThat(resultat.getTuples().size(), is(0));
        assertThat(resultat.hasAttribut("bases.id"), is(true));
        assertThat(resultat.hasAttribut("heroes.alias"), is(true));
    }

    @Test
    public void test_produitCartesien_projeter_selectionner() {
        Relation produit = relationBases.produitCartesien(relationHeroes);

        ExpressionLogique condition = new ExpressionLogique();
        condition.ajouterCondition(new ConditionSimple(
            new OperandeAttribut("bases", "id"),
            new OperandeAttribut("heroes", "base_id"),
            Comparateur.EGAL
        ));

        Relation resultat = produit
            .projeter(condition)
            .selectionner(Set.of("bases.nom", "heroes.alias"));

        assertThat(resultat.getTuples().size(), is(9));

        assertThat(resultat.hasAttribut("bases.nom"), is(true));
        assertThat(resultat.hasAttribut("heroes.alias"), is(true));

        assertThat(resultat.hasAttribut("bases.id"), is(false));
        assertThat(resultat.hasAttribut("heroes.base_id"), is(false));
    }


    @Test
    public void test_troisTables_projeter_selectionner() {
        Relation produit = relationBases.produitCartesien(List.of(relationHeroes, relationGadgets));

        ExpressionLogique condition = new ExpressionLogique();

        condition.ajouterCondition(new ConditionSimple(
            new OperandeAttribut("bases", "id"),
            new OperandeAttribut("heroes", "base_id"),
            Comparateur.EGAL
        ));

        condition.ajouterConnecteur(OperateurLogique.AND);

        condition.ajouterCondition(new ConditionSimple(
            new OperandeAttribut("heroes", "id"),
            new OperandeAttribut("gadgets", "hero_id"),
            Comparateur.EGAL
        ));

        Relation resultat = produit
            .projeter(condition)
            .selectionner(Set.of(
                "bases.nom",
                "heroes.alias",
                "gadgets.nom",
                "gadgets.puissance"
            ));

        assertThat(resultat.getTuples().size(), is(18));

        assertThat(resultat.hasAttribut("bases.nom"), is(true));
        assertThat(resultat.hasAttribut("heroes.alias"), is(true));
        assertThat(resultat.hasAttribut("gadgets.nom"), is(true));
        assertThat(resultat.hasAttribut("gadgets.puissance"), is(true));

        assertThat(resultat.hasAttribut("bases.id"), is(false));
        assertThat(resultat.hasAttribut("heroes.id"), is(false));
        assertThat(resultat.hasAttribut("gadgets.id"), is(false));
    }




}
