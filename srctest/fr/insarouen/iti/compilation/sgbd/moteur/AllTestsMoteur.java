package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestTuple.class,
    TestValeur.class,
    // TestBaseDeDonnees.class,
    TestRelation.class,
})

public class AllTestsMoteur {}
