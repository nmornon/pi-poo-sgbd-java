package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.compilation.sgbd.moteur.valeur.TestValeur;

@RunWith(Suite.class)
@SuiteClasses({
    TestTuple.class,
    TestValeur.class,
    TestBaseDeDonnees.class,
    TestRelation.class,
    TestTable.class,
    TestColonne.class,
})

public class AllTestsMoteur {}
