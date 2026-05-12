package fr.insarouen.iti.compilation.sgbd.moteur;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestIdentifiant.class,
    TestTuple.class,
    TestValeur.class,
})

public class AllTestsMoteur {}
